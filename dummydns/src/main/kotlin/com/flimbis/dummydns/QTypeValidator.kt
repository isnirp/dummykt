package com.flimbis.dummydns

import com.flimbis.dummydns.model.QType

class QTypeValidator {
    fun isValidQType(qtype: String): Boolean {
        return QType.values()
            .any { it.name == qtype }
    }
}