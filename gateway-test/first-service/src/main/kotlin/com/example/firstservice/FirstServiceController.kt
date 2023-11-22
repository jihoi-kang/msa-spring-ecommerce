package com.example.firstservice

import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("first-service")
class FirstServiceController(
    private val env: Environment,
) {

    @GetMapping("welcome")
    fun welcome(): String {
        return "Welcome to the First service"
    }

    @GetMapping("message")
    fun message(@RequestHeader("first-request") header: String): String {
        println("header=$header")
        return "Hello world in first request"
    }

    @GetMapping("check")
    fun check(request: HttpServletRequest): String {
        println("port=${request.serverPort}")
        return "Hi, there. This is a message from First service on PORT ${env.getProperty("local.server.port")}"
    }

}
