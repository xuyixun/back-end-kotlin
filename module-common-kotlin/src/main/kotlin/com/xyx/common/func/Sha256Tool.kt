package com.xyx.common.func

import com.google.common.hash.Hashing

class Sha256Tool {
    companion object {
        fun sha256(input: ByteArray): String {
            return Hashing.sha256()
                .hashBytes(input)
                .toString()
        }
    }
}