package pers.shennoter.utils

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*
import pers.shennoter.config.Custom

@OptIn(DelicateCoroutinesApi::class)
fun dQQ(){ //来自未来的消息
    GlobalEventChannel.parentScope(GlobalScope).subscribeAlways<GroupMessageEvent> { event ->
        val msg = event.message.content.split(' ')
        if(Custom.customDQQ.contains(msg[0])) {
            when(msg.size){
                1 -> subject.sendMessage("请输入内容")
                2 -> {
                    val forward = buildFwdMsg(sender.id, sender.nick, msg[1], 432000)
                    subject.sendMessage(forward)
                }
                3 -> {
                    if(msg[2].matches("[0-9- ]+".toRegex())) {
                        val forward = buildFwdMsg(sender.id, sender.nick, msg[1], msg[2].toInt())
                        subject.sendMessage(forward)
                    }
                    else{
                        subject.sendMessage("时间只能包含数字，单位：秒")
                    }
                }
            }
        }
    }
}

fun buildFwdMsg(id: Long, nick: String, msg:String, time: Int): ForwardMessage {
    val node = ForwardMessage.Node(
        id,
        (System.currentTimeMillis() / 1000 + time).toInt(),
        nick,
        PlainText(msg).toMessageChain()
    )
    val nodeList = listOf(node)
    val raw = RawForwardMessage(nodeList)
    return ForwardMessage(
        preview = ForwardMessage.DisplayStrategy.generatePreview(raw),
        title = ForwardMessage.DisplayStrategy.generateTitle(raw),
        brief = ForwardMessage.DisplayStrategy.generateBrief(raw),
        source = ForwardMessage.DisplayStrategy.generateSource(raw),
        summary = ForwardMessage.DisplayStrategy.generateSummary(raw),
        nodeList = nodeList
    )
}