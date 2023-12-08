package com.example.userservice.api

import com.example.userservice.service.UserService
import com.example.userservice.vo.Greeting
import com.example.userservice.vo.RequestUser
import com.example.userservice.vo.ResponseUser
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user-service")
class UserApiController(
    private val userService: UserService,
    private val greeting: Greeting,
    private val env: Environment
) {

    @PostMapping("users")
    fun createUser(
        @RequestBody @Validated user: RequestUser
    ): ResponseEntity<ResponseUser> {
        val userDto = userService.createUser(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUser(userDto.email, userDto.name, userDto.userId))
    }

    @GetMapping("health-check")
    fun status(): String {
        return "It's working in User Service on PORT ${env.getProperty("local.server.port")}"
    }

    @GetMapping("welcome")
    fun welcome(): String {
        return greeting.message
    }

    @GetMapping("users")
    fun getUsers(): ResponseEntity<List<ResponseUser>> {
        return ResponseEntity.ok(userService.getUsers().map { ResponseUser(it.email, it.name, it.userId, listOf()) })
    }

    @GetMapping("users/{userId}")
    fun getUser(
        @PathVariable("userId") userId: String,
    ): ResponseEntity<ResponseUser> {
        val userDto = userService.getUserById(userId)
        return ResponseEntity.ok(ResponseUser(userDto.email, userDto.name, userDto.userId, userDto.orders))
    }
}