package com.xyx.punishment.domain.po

import com.xyx.common.domain.po.CommonPo
import com.xyx.file.domain.po.CommonFile
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
data class PunishmentBillFile(
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn val punishmentBill: PunishmentBill, @ManyToOne(fetch = FetchType.LAZY) @JoinColumn val commonFile: CommonFile
) : CommonPo()
