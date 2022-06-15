package com.xyx.punishment.domain.dto

import org.springframework.web.multipart.MultipartFile

data class FilePreUpdateDto(val file: MultipartFile? = null) {
    fun check() = file == null
}
