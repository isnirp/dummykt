package com.flimbis.dummydns

import com.flimbis.dummydns.model.QType
import org.springframework.stereotype.Component

@Component
class QTypeValidator {
    fun isValidQType(qtype: String): Boolean {
        return QType.values()
            .any { it.name == qtype }
    }
}