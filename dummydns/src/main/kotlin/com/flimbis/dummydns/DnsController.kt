package com.flimbis.dummydns

import com.flimbis.dummydns.model.DnsRecordDto
import com.flimbis.dummydns.model.QType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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

    @GetMapping("/{qType}")
    fun findRecord(@PathVariable("qType") qType: String) = service.getRecord(qType)

}