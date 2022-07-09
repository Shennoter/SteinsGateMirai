package pers.shennoter.utils


import pers.shennoter.SteinsGate
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

fun saveFile(){
    val data = SteinsGate.dataFolder
    if(!data.exists()){
        data.mkdirs()
    }
    val file = File("${SteinsGate.dataFolder}/UniDiv.json")
    if(!file.exists()) {
        val path = Paths.get("${SteinsGate.dataFolder}/UniDiv.json")
        val inst = SteinsGate.getResourceAsStream("UniDiv.json")
        Files.copy(inst!!, path, StandardCopyOption.REPLACE_EXISTING)
    }
}