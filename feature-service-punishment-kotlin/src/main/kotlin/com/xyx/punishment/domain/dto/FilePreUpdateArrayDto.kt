package com.xyx.punishment.domain.dto

import com.xyx.file.domain.po.CommonFileType
import org.springframework.web.multipart.MultipartFile

data class FilePreUpdateArrayDto(val file: Set<MultipartFile>? = null, val type: CommonFileType) {
    fun check() = (file == null) || (file.isEmpty())
}
