package fago.net.testcontainerexample.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "\"USER\"")
class User(
    @Id
    var id: UUID? = null,
    var name: String,
    var age: Int
)
