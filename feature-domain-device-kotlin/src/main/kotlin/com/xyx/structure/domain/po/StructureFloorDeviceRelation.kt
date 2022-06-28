package com.xyx.structure.domain.po

import com.xyx.common.domain.po.CommonPo
import com.xyx.device.domain.po.Device
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class StructureFloorDeviceRelation(
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn val structureFloor: StructureFloor,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn val device: Device,
    var pointX: Short = 0,
    var pointY: Short = 0
) : CommonPo()
