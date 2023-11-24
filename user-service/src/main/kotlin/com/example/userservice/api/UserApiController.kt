package com.example.userservice.api

import com.example.userservice.service.UserService
import com.example.userservice.vo.Greeting
import com.example.userservice.vo.RequestUser
import com.example.userservice.vo.ResponseUser
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("")
class UserApiController(
    private val userService: UserService,
    private val greeting: Greeting,
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
        return "It's working in User Service"
    }

    @GetMapping("welcome")
    fun welcome(): String {
        return greeting.message
    }
}