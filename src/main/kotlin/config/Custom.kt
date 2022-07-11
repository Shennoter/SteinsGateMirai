package pers.shennoter.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object Custom : AutoSavePluginConfig("custom") {
    @ValueDescription("D-QQ的指令")
    val customDQQ: List<String> by value(listOf("dqq", "发送", "D-QQ"))
}