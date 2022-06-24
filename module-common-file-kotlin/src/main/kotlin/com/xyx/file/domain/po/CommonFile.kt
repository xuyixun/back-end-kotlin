package com.xyx.file.domain.po

import com.xyx.common.domain.po.CommonPo
import javax.persistence.*

@Entity
data class CommonFile(
    val originalFilename: String,
    val size: Int,
    val sha256: String,
    val suffix: String,
    @Enumerated(EnumType.STRING) val type: CommonFileType,
    val path: String,
    val watermarkImg: Boolean = false,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn val originalImg: CommonFile? = null
) : CommonPo() {
    companion object {
        fun create(uuid: String) = CommonFile("", 0, "", "", CommonFileType.IMAGE, "").apply { this.uuid = uuid }
    }
}

enum class CommonFileType {
    IMAGE,
    VIDEO,
    OTHER
}