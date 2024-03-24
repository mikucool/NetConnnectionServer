package com.hzz.netconnectionserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NetConnectionServerApplication

fun main(args: Array<String>) {
    runApplication<NetConnectionServerApplication>(*args)
}
