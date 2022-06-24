package com.xyx.file.domain.dto

import org.springframework.web.multipart.MultipartFile

data class FileUploadArrayDto(val file: Set<MultipartFile>?) {
    fun check() = file == null || file.isEmpty()
}
