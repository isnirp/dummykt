package com.flimbis.dummydns

import com.flimbis.dummydns.model.DnsRecord
import com.flimbis.dummydns.model.DnsRecordDto
import com.flimbis.dummydns.model.QType
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class DnsServiceTest {
    val validator: QTypeValidator = mockk()
    val records: MutableList<DnsRecord> = mutableListOf()

    val service = DnsService(records, validator)

    @Test
    fun `create record`() {
        val recordDto: DnsRecordDto = DnsRecordDto("www.google.com", "CNAME", "google")

        val result = service.createRecord(recordDto)

        assertThat(result).isNotNull()
        assertThat(records).isNotEmpty()
    }

    @Test
    fun `get record by qType`() {
        val qType = "CNAME"
        records.add(DnsRecord("www.google.com", QType.CNAME, "google", 3000))

        every { validator.isValidQType(qType) } returns true

        val result = service.getRecord(qType)

        assertThat(result).isNotNull()
    }

}