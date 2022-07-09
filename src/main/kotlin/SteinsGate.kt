package pers.shennoter

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object SteinsGate : KotlinPlugin(
    JvmPluginDescription(
        id = "pers.shennoter.steinsgate",
        name = "Steins Gate",
        version = "0.1.0",
    ) {
        author("Shennoter")
    }
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }
    }
}