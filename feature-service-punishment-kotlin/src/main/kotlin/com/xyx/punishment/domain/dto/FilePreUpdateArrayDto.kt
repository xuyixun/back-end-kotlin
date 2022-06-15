package com.xyx.punishment.domain.dto

import org.springframework.web.multipart.MultipartFile

data class FilePreUpdateArrayDto(val file: Set<MultipartFile>? = null) {
    fun check() = file == null || file.isEmpty()
}
