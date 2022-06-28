package com.xyx.device.domain.po

import com.xyx.common.domain.po.CommonPo
import com.xyx.common.domain.po.CommonPoDeleted
import com.xyx.common.domain.po.CommonPoEnabled
import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
data class DeviceBrand(var name: String) : CommonPo(), CommonPoDeleted, CommonPoEnabled {
    override var deleted: Boolean = false
    override var deletedTime: LocalDateTime? = null
    override var enabled: Boolean = false

    companion object {
        fun create(uuid: String) = DeviceBrand("").apply { this.uuid = uuid }
        fun create() = DeviceBrand("")
    }
}
