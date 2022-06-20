package com.xyx.punishment.domain.dto

import com.xyx.file.domain.po.CommonFileType
import org.springframework.web.multipart.MultipartFile

data class FilePreUpdateDto(val file: MultipartFile? = null, val type: CommonFileType) {
    fun check() = file == null
}
