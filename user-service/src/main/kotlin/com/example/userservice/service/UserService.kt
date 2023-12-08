package com.example.userservice.service

import com.example.userservice.dto.UserDto
import com.example.userservice.entity.UserEntity
import com.example.userservice.vo.RequestUser

interface UserService {
    fun createUser(user: RequestUser): UserDto
    fun getUserById(userId: String): UserDto
    fun getUsers(): List<UserEntity>
}