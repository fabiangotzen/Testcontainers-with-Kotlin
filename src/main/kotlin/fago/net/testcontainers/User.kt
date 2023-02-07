package fago.net.testcontainers

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "\"USER\"")
class User(
    @Id
    var id: UUID,
    var name: String,
    var age: Int
)
