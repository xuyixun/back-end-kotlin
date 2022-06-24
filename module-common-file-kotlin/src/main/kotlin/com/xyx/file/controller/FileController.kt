package com.xyx.file.controller

import com.xyx.common.func.ErrorCodeCommon
import com.xyx.common.func.Return
import com.xyx.common.func.returnCode
import com.xyx.common.func.returnSuccess
import com.xyx.file.domain.dto.FileUploadArrayDto
import com.xyx.file.domain.dto.FileUploadDto
import com.xyx.file.func.FileUpload
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Api(tags = ["文件"])
@RestController
@RequestMapping("api/file")
class FileController(private val fileUpload: FileUpload) {
    @ApiOperation(value = "上传-单个")
    @PostMapping(value = ["v1/update"], headers = ["content-type=multipart/form-data"])
    fun upload(dto: FileUploadDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        return returnSuccess(
            fileUpload.upload(
                dto.file!!.inputStream, dto.file.originalFilename!!
            ).uuid
        )
    }

    @ApiOperation(value = "上传-批量")
    @PostMapping(value = ["v1/update_array"], headers = ["content-type=multipart/form-data"])
    fun upload(dto: FileUploadArrayDto): Return {
        if (dto.check()) {
            return returnCode(ErrorCodeCommon.COMMON_PARAMS_ERROR)
        }
        val uuidSet = arrayListOf<String>()
        for (v in dto.file!!) {
            fileUpload.upload(v.inputStream, v.originalFilename!!)
                .apply {
                    uuidSet.add(uuid)
                }
        }
        return returnSuccess(uuidSet)
    }
}