package com.example.demo.data

import java.util.UUID
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class Customer(
    val id: String,
    @field:NotBlank
    var firstName: String,
    @field:NotBlank
    var lastName: String,
    val address: Address
) {
    constructor() : this(
        UUID.randomUUID().toString(),
        "",
        "",
        Address("", "", "", "")
    )
}

data class Address(
    var street: String,
    var number: String,
    @field:Pattern(regexp = "[0-9]{5}", message = "must be 5 integers")
    var zipcode: String,
    var city: String
)
