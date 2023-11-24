package com.example.userservice.vo

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class RequestUser(
    @JsonProperty("email")
    @field:NotNull(message = "Email cannot be null")
    @field:Size(min = 2, message = "Email not be less than two characters")
    @field:Email
    private val _email: String?,
    @JsonProperty("name")
    @field:NotNull(message = "Name cannot be null")
    @field:Size(min = 2, message = "Name not be less than two characters")
    private val _name: String?,
    @JsonProperty("pwd")
    @field:NotNull(message = "Password cannot be null")
    @field:Size(min = 8, message = "Name must be equal or more than 8 characters")
    private val _pwd: String?,
) {
    val email: String
        get() = _email!!
    val name: String
        get() = _name!!
    val pwd: String
        get() = _pwd!!
}