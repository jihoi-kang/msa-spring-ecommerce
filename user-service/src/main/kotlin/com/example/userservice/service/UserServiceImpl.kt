package com.example.userservice.service

import com.example.userservice.dto.UserDto
import com.example.userservice.entity.UserEntity
import com.example.userservice.repository.UserRepository
import com.example.userservice.vo.RequestUser
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

@Service
@Transactional(readOnly = true)
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
) : UserService {
    @Transactional
    override fun createUser(user: RequestUser): UserDto {
        val userDto = UserDto(
            UUID.randomUUID().toString(),
            user.email,
            user.name,
            user.pwd,
            LocalDateTime.now(),
            passwordEncoder.encode(user.pwd),
        )
        userRepository.save(UserEntity(userDto.email, userDto.name, userDto.userId, userDto.encryptedPwd))

        return userDto
    }
}