package com.xyx.structure.domain.po

import com.xyx.common.domain.po.CommonPo
import com.xyx.common.domain.po.CommonPoDeleted
import com.xyx.common.domain.po.CommonPoEnabled
import java.time.LocalDateTime
import javax.persistence.Entity

@Entity
data class Structure(
    var name: String, var longitude: String, var latitude: String
) : CommonPo(), CommonPoDeleted, CommonPoEnabled {
    override var deleted: Boolean = false
    override var deletedTime: LocalDateTime? = null
    override var enabled: Boolean = false

    companion object {
        fun create(uuid: String) = Structure("", "", "").apply { this.uuid = uuid }
        fun create() = Structure("", "", "")
    }
}
