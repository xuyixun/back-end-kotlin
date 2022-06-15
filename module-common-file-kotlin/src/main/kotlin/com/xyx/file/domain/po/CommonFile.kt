package com.xyx.file.domain.po

import com.xyx.common.domain.po.CommonPo
import javax.persistence.Entity

@Entity
data class CommonFile(
    val originalFilename: String,
    val size: Int,
    val sha256: String,
    val suffix: String,
    val path: String
) : CommonPo() {
    companion object {
        fun create(uuid: String) = CommonFile("", 0, "", "", "").apply { this.uuid = uuid }
    }
}
