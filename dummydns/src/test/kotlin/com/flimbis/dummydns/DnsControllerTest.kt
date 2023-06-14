package com.flimbis.dummydns

import com.flimbis.dummydns.model.DnsRecordDto
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class DnsControllerTest(@Autowired val mockmvc: MockMvc) {
    @MockkBean
    lateinit var service: DnsService

    @Test
    fun `add record to list`() {
        val dnsRecordDto = DnsRecordDto("www.google.com", "CNAME", "google")
        every { service.createRecord(any()) } returns 1

        mockmvc.perform(
            MockMvcRequestBuilders.post("/api")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""{"qName":"www.google.com", "qType":"CNAME"}""")
        )
            .andExpect(status().isCreated)
    }
}