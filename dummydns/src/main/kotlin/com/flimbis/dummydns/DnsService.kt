package com.flimbis.dummydns

import com.flimbis.dummydns.model.DnsRecord
import com.flimbis.dummydns.model.DnsRecordDto
import com.flimbis.dummydns.model.QType
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class DnsService(val records: MutableList<DnsRecord>, val validator: QTypeValidator) {
    // ToDo respond to NS, A, CNAME and SOA requests
    // ToDo fetch records for all domains belonging to websites in our database
    //The DNS server will forward A and CNAME requests as ANY requests to the backend,
    // for which the response is expected to always contain all records for the domain.
    fun createRecord(recordDto: DnsRecordDto): Int {
        val record = DnsRecord(recordDto.qName, QType.valueOf(recordDto.qType), recordDto.content, 3000)
        records.add(record)
        return records.indexOf(record)
    }

    fun getRecord(qType: String): List<DnsRecordDto> {
        if (!validator.isValidQType(qType)) throw RuntimeException("not found type")

        val validRecord = records
//            .filter{record -> record.qType == QType.valueOf(qType)}
            .filter { it.qType == QType.valueOf(qType) }
            .map { DnsRecordDto(it.domain, it.qType.toString(), it.content) }

        if (validRecord.isEmpty()) throw RuntimeException("not found record")

        return validRecord
    }

    fun getRecordAll(): List<DnsRecord> {
        return records
    }
}