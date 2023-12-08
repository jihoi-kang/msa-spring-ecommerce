package com.example.userservice.dto

import com.example.userservice.vo.ResponseOrder
import java.time.LocalDateTime

data class UserDto(
    val userId: String,
    val email: String,
    val name: String,
    val pwd: String,
    val createdAt: LocalDateTime,
    val encryptedPwd: String,
    val orders: MutableList<ResponseOrder> = mutableListOf(),
)