package com.xyx.file.func

import com.xyx.common.func.Sha256Func
import com.xyx.file.domain.po.CommonFile
import com.xyx.file.domain.repository.CommonFileRepository
import org.springframework.stereotype.Component
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.imageio.ImageIO

@Component
class WatermarkFunc(private val commonFileRepository: CommonFileRepository) {
    fun text(originalFile: CommonFile, vararg textArray: String): CommonFile {
        val byteArray = text(FileInputStream(originalFile.path).use { ImageIO.read(it) }, *textArray).use { it.toByteArray() }
        val sha256 = Sha256Func.sha256(byteArray)
        val p = path(sha256)
        Files.createDirectories(p.parent)
        Files.write(p, byteArray, StandardOpenOption.CREATE)
        return commonFileRepository.save(CommonFile(originalFile.originalFilename, byteArray.size, sha256, "png", originalFile.type, p.toString(), true, originalFile))
    }

    private fun text(targetImg: BufferedImage, vararg textArray: String): ByteArrayOutputStream {
        val width = targetImg.width
        val height = targetImg.height
        val watermarkImg = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        watermarkImg.createGraphics()
            .apply {
                val fontSize = 18
                drawImage(targetImg, 0, 0, width, height, null)
                color = Color.RED
                font = Font("宋体", Font.BOLD, fontSize)
                for ((index, text) in textArray.withIndex()) {
                    val y = height - fontSize * 2 * (textArray.size - index)
                    if (y >= 0) {
                        drawString(text, 10, y)
                    }
                }
                dispose()
            }
        return ByteArrayOutputStream().apply {
            ImageIO.write(watermarkImg, "png", this)
        }
    }

    private fun path(name: String): Path = Paths.get(
        "files",
        LocalDate.now()
            .format(DateTimeFormatter.BASIC_ISO_DATE),
        "watermark",
        "$name.png"
    )
}