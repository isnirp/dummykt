package com.flimbis.dummydns

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class DnsExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleNotFoundException(ex: RuntimeException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}