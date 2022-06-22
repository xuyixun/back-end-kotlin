package com.xyx.file.domain.dto

import com.xyx.file.domain.po.CommonFileType
import org.springframework.web.multipart.MultipartFile

data class FileUploadDto(val file: MultipartFile? = null, val type: CommonFileType) {
    fun check() = file == null
}
