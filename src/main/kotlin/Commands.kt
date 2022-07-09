package pers.shennoter

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.messageChainOf
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import pers.shennoter.config.Config
import pers.shennoter.utils.drawMeter
import pers.shennoter.utils.strDiv2List
import pers.shennoter.utils.uniDiv


object DivergenceMeter : SimpleCommand(
    SteinsGate, "div", "世界线",
    description = "生成世界线变动率，无参数则随机生成"
) {
    @Handler
    suspend fun CommandSender.divergenceMeter(div:String = "") {
        val divergence: MutableList<Int>
        if (div != "") {  // 手动输入世界线变动率
            divergence = strDiv2List(div + "0000000") // div不足7位用0补全
            val image = drawMeter(divergence)
            if (image != null) {
                subject?.sendImage(image)
            } else {
                subject?.sendMessage("生成失败")
            }
        } else { // 随机生成世界线变动率
            if((0..100).random() <= Config.uniqueProbe){ // 特殊世界线检定
                val uniqueDivergence = uniDiv()
                divergence = strDiv2List(uniqueDivergence.first)
                val msg = uniqueDivergence.second
                val image = drawMeter(divergence)
                if (image != null) {
                    subject?.sendMessage(messageChainOf(image.uploadAsImage(subject!!), PlainText("\n" + msg)))
                } else {
                    subject?.sendMessage("生成失败")
                }
            }
            else { // 生成非特殊世界线
                divergence = mutableListOf((-1..4).random())
                (0..5).forEach {
                    divergence.add((0..9).random())
                }
                val image = drawMeter(divergence)
                if (image != null) {
                    subject?.sendImage(image)
                } else {
                    subject?.sendMessage("生成失败")
                }
            }
        }

    }
}

object UniqueDivergenceMeter : SimpleCommand(
    SteinsGate, "udiv",
    description = "生成特殊世界线变动率"
) {
    @Handler
    suspend fun CommandSender.uniqueDivergenceMeter() {
                val uniqueDivergence = uniDiv()
                val divergence = strDiv2List(uniqueDivergence.first)
                val msg = uniqueDivergence.second
                val image = drawMeter(divergence)
                if (image != null) {
                    subject?.sendMessage(messageChainOf(image.uploadAsImage(subject!!), PlainText("\n" + msg)))
                } else {
                    subject?.sendMessage("生成失败")
                }
    }
}