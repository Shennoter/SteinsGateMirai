package pers.shennoter.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {
    @ValueDescription("生成特殊世界线变动率的概率，默认为50%")
    val uniqueProbe: Int by value(50)
}