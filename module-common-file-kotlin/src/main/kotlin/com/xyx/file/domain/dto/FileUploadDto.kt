package com.xyx.file.domain.dto

import org.springframework.web.multipart.MultipartFile

data class FileUploadDto(val file: MultipartFile?) {
    fun check() = file == null
}
