package com.flimbis.dummydns.model

data class DnsRecord(val domain: String, val qType: QType, val content: String, val ttl: Long)
data class DnsRecordDto(val qName: String, val qType: String, val content: String="")

enum class QType {
    NS, A, CNAME, ANY
}