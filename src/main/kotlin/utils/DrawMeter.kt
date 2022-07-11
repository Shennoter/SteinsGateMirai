package pers.shennoter.utils

import com.google.gson.Gson
import pers.shennoter.SteinsGate
import pers.shennoter.bean.UniqueDivergence
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream
import javax.imageio.ImageIO

// 拼接辉光管图片
fun drawMeter(divergence: MutableList<Int>): InputStream? {
    var image: BufferedImage? = number(divergence[0])
    if(image != null) {
        image = mergeImage(true, image, ImageIO.read(SteinsGate.getResourceAsStream("dot.png")))
        for (i in (1..6)) {
            image = mergeImage(true, image!!, number(divergence[i])!!)
        }
        val os = ByteArrayOutputStream()
        ImageIO.write(image, "jpeg", os)
        return ByteArrayInputStream(os.toByteArray())
    }
    else{
        return null
    }
}

// 取出对应数字的辉光管图片
fun number(num: Int): BufferedImage? {
    val numPic = when(num){
        -1 -> ImageIO.read(SteinsGate.getResourceAsStream("none.png"))
        0 -> ImageIO.read(SteinsGate.getResourceAsStream("0.png"))
        1 -> ImageIO.read(SteinsGate.getResourceAsStream("1.png"))
        2 -> ImageIO.read(SteinsGate.getResourceAsStream("2.png"))
        3 -> ImageIO.read(SteinsGate.getResourceAsStream("3.png"))
        4 -> ImageIO.read(SteinsGate.getResourceAsStream("4.png"))
        5 -> ImageIO.read(SteinsGate.getResourceAsStream("5.png"))
        6 -> ImageIO.read(SteinsGate.getResourceAsStream("6.png"))
        7 -> ImageIO.read(SteinsGate.getResourceAsStream("7.png"))
        8 -> ImageIO.read(SteinsGate.getResourceAsStream("8.png"))
        9 -> ImageIO.read(SteinsGate.getResourceAsStream("9.png"))
        else -> null
    }
    return numPic
}

// 将String世界线变动率转换为MutableList<Int>
fun strDiv2List(div: String):MutableList<Int>{
    if(div[0] == '-'){
        val divergence = mutableListOf(-1)
        val buffer= div.split("").subList(2, 9)
        buffer.forEach{
            divergence.add(it.toInt())
        }
        return divergence
    }
    else {
        val buffer = div.split("").subList(1, 8)
        val divergence = mutableListOf(0)
        buffer.forEach {
            divergence.add(it.toInt())
        }
        return divergence.subList(1, 8)
    }
}

//  随机显示一个特殊世界线
fun uniDiv():Pair<String,String>{
    val gson = Gson()
    val file = File("${SteinsGate.dataFolder}/UniDiv.json")
    val div = gson.fromJson(file.readText(), UniqueDivergence::class.java)
    val index = (0 until div.size).random()
    val number = div[index].number[0] + "." + div[index].number.substring(1,7) + "%" + "\n"
    val abstract = if(div[index].abstract == ""){
        div[index].abstract
    }
    else{
        "● " + div[index].abstract
    }
    var detail = ""
    if(div[index].detail != "") {
        div[index].detail.split('\n').forEach {
            detail += "○ $it\n"
        }
        detail = "\n" + detail
    }
    return if(abstract != ""){
        Pair(div[index].number,number + abstract + detail)
    }
    else Pair(div[index].number,number + detail)
}

//拼接图片
fun mergeImage(isHorizontal: Boolean, vararg imgs: BufferedImage): BufferedImage {
    // 生成新图片
    val destImage: BufferedImage?
    // 计算新图片的长和高
    var allw = 0
    var allh = 0
    var allwMax = 0
    var allhMax = 0
    // 获取总长、总宽、最长、最宽
    for (i in imgs.indices) {
        val img = imgs[i]
        allw += img.width
        allh += img.height
        if (img.width > allwMax) {
            allwMax = img.width
        }
        if (img.height > allhMax) {
            allhMax = img.height
        }
    }
    // 创建新图片
    destImage = if (isHorizontal) {
        BufferedImage(allw, allhMax, BufferedImage.TYPE_INT_RGB)
    } else {
        BufferedImage(allwMax, allh, BufferedImage.TYPE_INT_RGB)
    }
    // 合并所有子图片到新图片
    var wx = 0
    var wy = 0
    for (i in imgs.indices) {
        val img = imgs[i]
        val w1 = img.width
        val h1 = img.height
        // 从图片中读取RGB
        var imageArrayOne: IntArray? = IntArray(w1 * h1)
        imageArrayOne = img.getRGB(0, 0, w1, h1, imageArrayOne, 0, w1) // 逐行扫描图像中各个像素的RGB到数组中
        if (isHorizontal) { // 水平方向合并
            destImage.setRGB(wx, 0, w1, h1, imageArrayOne, 0, w1) // 设置上半部分或左半部分的RGB
        } else { // 垂直方向合并
            destImage.setRGB(0, wy, w1, h1, imageArrayOne, 0, w1) // 设置上半部分或左半部分的RGB
        }
        wx += w1
        wy += h1
    }
    return destImage
}