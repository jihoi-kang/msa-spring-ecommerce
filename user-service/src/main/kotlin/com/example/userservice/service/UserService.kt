package com.example.userservice.service

import com.example.userservice.dto.UserDto
import com.example.userservice.vo.RequestUser

interface UserService {
    fun createUser(user: RequestUser): UserDto
}