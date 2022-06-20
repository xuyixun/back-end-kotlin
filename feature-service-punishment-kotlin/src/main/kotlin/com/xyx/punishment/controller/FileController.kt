package com.xyx.punishment.controller

import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.file.func.FileUpload
import com.xyx.punishment.constant.ErrorCodePunishment
import com.xyx.punishment.domain.dto.FilePreUpdateArrayDto
import com.xyx.punishment.domain.dto.FilePreUpdateDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["文件"])
@RestController
@RequestMapping("api/file")
class FileController(private val fileUpload: FileUpload) {
    @ApiOperation(value = "预上传-单个")
    @PostMapping(value = ["v1/update"], headers = ["content-type=multipart/form-data"])
    fun updatePre(dto: FilePreUpdateDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val commonFile = fileUpload.update(
            dto.file!!.inputStream, dto.file.originalFilename!!, dto.type
        ) ?: return returnCode(ErrorCodePunishment.UPLOAD_001)
        return returnSuccess(commonFile.uuid)
    }

    @ApiOperation(value = "预上传-批量")
    @PostMapping(value = ["v1/update_array"], headers = ["content-type=multipart/form-data"])
    fun arrayUpdatePre(dto: FilePreUpdateArrayDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val uuidSet = arrayListOf<String>()
        for (v in dto.file!!) {
            val commonFile = this.fileUpload.update(v.inputStream, v.originalFilename!!, dto.type) ?: return returnCode(ErrorCodePunishment.UPLOAD_001)
            uuidSet.add(commonFile.uuid)
        }
        return returnSuccess(uuidSet)
    }
}