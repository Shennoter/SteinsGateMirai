package pers.shennoter


import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info
import pers.shennoter.config.Config
import pers.shennoter.utils.dQQ
import pers.shennoter.utils.saveFile


object SteinsGate : KotlinPlugin(
    JvmPluginDescription(
        id = "pers.shennoter.steinsgate",
        name = "Steins Gate",
        version = "0.2.0",
    ) {
        author("Shennoter")
    }
) {
    override fun onEnable() {
        logger.info { "石头门插件已启动" }
        CommandManager.registerCommand(DivergenceMeter)
        CommandManager.registerCommand(UniqueDivergenceMeter)
        CommandManager.registerCommand(SGTimelineobj)
        Config.reload()
        saveFile()
        dQQ()
        logger.info { "EL PSY KONGROO" }
    }

    override fun onDisable() {
        Config.save()
        CommandManager.unregisterCommand(DivergenceMeter)
        CommandManager.unregisterCommand(UniqueDivergenceMeter)
        CommandManager.unregisterCommand(SGTimelineobj)
        logger.info("在百分之一的墙的对面，我一定存在着。")
    }
}

