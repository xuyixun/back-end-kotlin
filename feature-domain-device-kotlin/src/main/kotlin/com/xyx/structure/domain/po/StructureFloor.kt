package com.xyx.structure.domain.po

import com.xyx.common.domain.po.CommonPo
import com.xyx.common.domain.po.CommonPoDeleted
import com.xyx.file.domain.po.CommonFile
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class StructureFloor(@ManyToOne(fetch = FetchType.LAZY) @JoinColumn val structure: Structure, var number: Byte, @ManyToOne(fetch = FetchType.LAZY) @JoinColumn var mapImage: CommonFile?) :
    CommonPo(), CommonPoDeleted {
    override var deleted: Boolean = false
    override var deletedTime: LocalDateTime? = null
}