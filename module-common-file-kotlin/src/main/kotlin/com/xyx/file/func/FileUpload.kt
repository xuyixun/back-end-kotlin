package com.xyx.file.func

import com.xyx.common.func.Sha256Func
import com.xyx.file.domain.po.CommonFile
import com.xyx.file.domain.po.CommonFileType
import com.xyx.file.domain.repository.CommonFileRepository
import org.springframework.stereotype.Component
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class FileUpload(private val commonFileRepository: CommonFileRepository) {
    fun upload(fileSteam: InputStream, originalFilename: String): CommonFile {
        val byteArray = fileSteam.use { it.readAllBytes() }
        val sha256 = Sha256Func.sha256(byteArray)
        val commonFileOptional = this.commonFileRepository.findBySha256(sha256)
        return if (commonFileOptional.isPresent) {
            commonFileOptional.get()
        } else {
            val suffix = suffix(originalFilename)
            val p = path(sha256, suffix)
            Files.createDirectories(p.parent)
            Files.write(p, byteArray, StandardOpenOption.CREATE)
            commonFileRepository.save(CommonFile(originalFilename, byteArray.size, sha256, suffix, checkType(suffix), p.toString()))
        }
    }

    private fun suffix(originalFilename: String): String = originalFilename.substring(originalFilename.lastIndexOf("."))
        .lowercase(Locale.ROOT)

    private fun path(name: String, suffix: String): Path = Paths.get(
        "files",
        LocalDate.now()
            .format(DateTimeFormatter.BASIC_ISO_DATE),
        name + suffix
    )

    private fun checkType(suffix: String): CommonFileType = when (suffix) {
        "jpg", "png" -> CommonFileType.IMAGE
        "mp4" -> CommonFileType.VIDEO
        else -> CommonFileType.OTHER
    }
}