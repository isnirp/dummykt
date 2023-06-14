package com.flimbis.dummydns

import com.flimbis.dummydns.model.DnsRecordDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DnsController(val service: DnsService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addRecord(@RequestBody record: DnsRecordDto): Int = service.createRecord(record)

}