package com.xyx.file.`fun`

import com.xyx.common.func.Sha256Tool
import com.xyx.file.domain.po.CommonFile
import com.xyx.file.domain.repository.CommonFileRepository
import org.springframework.stereotype.Component
import java.io.IOException
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
    fun update(fileSteam: InputStream, originalFilename: String?): CommonFile? {
        if (originalFilename == null) {
            return null
        }
        try {
            val byteInput = fileSteam.readAllBytes()
            fileSteam.close()
            val sha256 = Sha256Tool.sha256(byteInput)
            val commonFileOptional = this.commonFileRepository.findBySha256(sha256)
            if (commonFileOptional.isPresent) {
                return commonFileOptional.get()
            }
            val suffix = suffix(originalFilename)
            val p = path(sha256, suffix)
            Files.createDirectories(p.parent)
            Files.write(p, byteInput, StandardOpenOption.CREATE)
            return commonFileRepository.save(CommonFile(originalFilename, byteInput.size, sha256, suffix, p.toString()))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun suffix(originalFilename: String): String = originalFilename.substring(originalFilename.lastIndexOf("."))
        .lowercase(Locale.ROOT)

    private fun path(name: String, suffix: String): Path = Paths.get(
        "files",
        LocalDate.now()
            .format(DateTimeFormatter.BASIC_ISO_DATE),
        name + suffix
    )
}