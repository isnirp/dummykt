package com.flimbis.dummydns

import com.flimbis.dummydns.model.DnsRecord
import com.flimbis.dummydns.model.DnsRecordDto
import com.flimbis.dummydns.model.QType
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.RuntimeException


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

    @Test
    fun `throw exception when qType is invalid`() {
        val qType = "NX"
        every { validator.isValidQType(qType) } returns false

        assertThatThrownBy{ service.getRecord(qType) }
            .isInstanceOf(RuntimeException::class.java)
            .hasMessage("not found type")
    }

    @Test
    fun `throw exception when record not found`() {
        val qType = "CNAME"
        records.add(DnsRecord("www.google.com", QType.NS, "google", 3000))

        every { validator.isValidQType(qType) } returns true

        assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy { service.getRecord(qType) }
            .withMessage("not found record")
    }

}