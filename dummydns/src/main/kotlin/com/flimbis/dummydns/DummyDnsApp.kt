package com.flimbis.dummydns

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DummyDnsApp

fun main(args: Array<String>) {
    runApplication<DummyDnsApp>(*args)
}