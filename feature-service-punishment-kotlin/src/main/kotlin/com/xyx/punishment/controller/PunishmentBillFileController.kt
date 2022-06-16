package com.xyx.punishment.controller

import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.file.domain.po.CommonFile
import com.xyx.file.`fun`.FileUpload
import com.xyx.punishment.constant.ErrorCodePunishment
import com.xyx.punishment.domain.dto.*
import com.xyx.punishment.domain.po.PunishmentBill
import com.xyx.punishment.domain.po.PunishmentBillFile
import com.xyx.punishment.domain.repository.PunishmentBillFileRepository
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["处罚-处罚单文件"])
@RestController
@RequestMapping("api/punishment/punishment_bill_file")
class PunishmentBillFileController(private val punishmentBillFileRepository: PunishmentBillFileRepository, private val fileUpload: FileUpload) {
    @ApiOperation(value = "预上传-单个")
    @PostMapping(value = ["v1/pre_update"], headers = ["content-type=multipart/form-data"])
    fun updatePre(dto: FilePreUpdateDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val commonFile = fileUpload.update(
            dto.file!!.inputStream, dto.file.originalFilename
        ) ?: return returnCode(ErrorCodePunishment.UPLOAD_001)
        return returnSuccess(commonFile.uuid)
    }

    @ApiOperation(value = "预上传-批量")
    @PostMapping(value = ["v1/pre_update_array"], headers = ["content-type=multipart/form-data"])
    fun arrayUpdatePre(dto: FilePreUpdateArrayDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val uuidSet = arrayListOf<String>()
        for (v in dto.file!!) {
            val commonFile = this.fileUpload.update(v.inputStream, v.originalFilename) ?: return returnCode(ErrorCodePunishment.UPLOAD_001)
            uuidSet.add(commonFile.uuid)
        }
        return returnSuccess(uuidSet)
    }

    @Transactional
    @ApiOperation(value = "项目图片关联删除-单个")
    @DeleteMapping(value = ["v1/delete"])
    fun delete(dto: FileDeleteDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        this.punishmentBillFileRepository.deleteByPunishmentBillUuidAndCommonFileUuid(dto.uuid, dto.fileUUid)
        return returnSuccess()
    }

    @Transactional
    @ApiOperation(value = "项目图片关联删除-所有")
    @DeleteMapping(value = ["v1/delete_all"])
    fun deleteAll(dto: FileDeleteAllDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        this.punishmentBillFileRepository.deleteByPunishmentBillUuid(dto.uuid)
        return returnSuccess()
    }

    @Transactional
    @ApiOperation(value = "预上传关联项目")
    @PostMapping(value = ["v1/relation"])
    fun relationItemSf(dto: FileRelationDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        dto.fileUUid.forEach { v ->
            if (punishmentBillFileRepository.existsByPunishmentBillUuidAndCommonFileUuid(dto.uuid, v)
                    .not()
            ) {
                this.punishmentBillFileRepository.save(
                    PunishmentBillFile(PunishmentBill.create(dto.uuid), CommonFile.create(v))
                )
            }
        }
        return returnSuccess()
    }
}