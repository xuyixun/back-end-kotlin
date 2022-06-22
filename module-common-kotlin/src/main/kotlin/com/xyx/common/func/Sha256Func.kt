package com.xyx.common.func

import com.google.common.hash.Hashing

object Sha256Func {
    fun sha256(input: ByteArray) = Hashing.sha256()
        .hashBytes(input)
        .toString()
}