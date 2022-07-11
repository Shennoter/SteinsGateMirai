package pers.shennoter

import com.google.gson.Gson
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.Contact.Companion.sendImage
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.messageChainOf
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import pers.shennoter.bean.SGTimeline
import pers.shennoter.config.Config
import pers.shennoter.utils.drawMeter
import pers.shennoter.utils.strDiv2List
import pers.shennoter.utils.uniDiv
import java.io.File


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

object SGTimelineobj : SimpleCommand(
    SteinsGate, "timeline","年表",
    description = "抽取一个事件年份"
) {
    @Handler
    suspend fun CommandSender.sgTimeline(year: String = "") {
        val gson = Gson()
        val file = File("${SteinsGate.dataFolder}/Timeline.json")
        val sgEvent = gson.fromJson(file.readText(), SGTimeline::class.java)
        if(year != ""){
            var flag = false
            sgEvent.forEach{
                if(it.year == year){
                    var msg = ""
                    msg += "世界线收束范围：${it.attractorField}\n"
                    msg += "年份：${it.year}\n"
                    if(it.date != ""){
                        msg += "日期：${it.date}\n"
                    }
                    msg += "事件：${it.detail}"
                    subject?.sendMessage(msg)
                    flag = true
                }
            }
            if(!flag){
                subject?.sendMessage("无此年份")
            }
        }
        else{
            val instance = sgEvent[(0 until sgEvent.size).random()]
            var msg = ""
            msg += "世界线收束范围：${instance.attractorField}\n"
            msg += "年份：${instance.year}\n"
            if(instance.date != ""){
                msg += "日期：${instance.date}\n"
            }
            msg += "事件：${instance.detail}"
            subject?.sendMessage(msg)
        }
    }
}