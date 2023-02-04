package fago.net.testcontainerexample.domain

import java.util.*

data class UserDto(val id: UUID = UUID.randomUUID(), val name: String, val age: Int)
