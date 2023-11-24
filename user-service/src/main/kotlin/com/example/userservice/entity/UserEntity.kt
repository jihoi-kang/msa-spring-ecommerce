package com.example.userservice.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Column(nullable = false, length = 50, unique = true)
    private val email: String,
    @Column(nullable = false, length = 50)
    private val name: String,
    @Column(nullable = false, unique = true)
    private val userId: String,
    @Column(nullable = false, unique = true)
    private val encryptedPwd: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0L
}