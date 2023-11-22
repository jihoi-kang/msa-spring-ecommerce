package com.example.secondservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("second-service")
class SecondServiceController {

    @GetMapping("welcome")
    fun welcome(): String {
        return "Welcome to the Second service"
    }

    @GetMapping("message")
    fun message(@RequestHeader("second-request") header: String): String {
        println("header=$header")
        return "Hello world in second request"
    }

    @GetMapping("check")
    fun check(): String {
        return "Hi, there. This is a message from Second service"
    }

}
