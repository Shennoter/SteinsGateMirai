package pers.shennoter

import java.awt.image.BufferedImage

fun drawMeter(divergence: MutableList): BufferedImage{
    var image: BufferedImage = number(divergence[0])
    image = mergeImage(true, image, SteinsGate.getResource("0.jpg"))
    for(i in (1..6)){
        image = mergeImage(true, image, number(i))
    }
    return image
}

fun number(num: Int): BufferedImage{
    val numPic = when(num){
        -1 -> SteinsGate.getResource("0.jpg")
        0 -> SteinsGate.getResource("0.jpg")
        1 -> SteinsGate.getResource("1.jpg")
        2 -> SteinsGate.getResource("2.jpg")
        3 -> SteinsGate.getResource("3.jpg")
        4 -> SteinsGate.getResource("4.jpg")
        5 -> SteinsGate.getResource("5.jpg")
        6 -> SteinsGate.getResource("6.jpg")
        7 -> SteinsGate.getResource("7.jpg")
        8 -> SteinsGate.getResource("8.jpg")
        9 -> SteinsGate.getResource("9.jpg")
        else -> return null
    }
    return numPic
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