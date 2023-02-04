package fago.net.testcontainerexample.infrastructure

import fago.net.testcontainerexample.domain.User
import org.springframework.data.repository.CrudRepository
import java.util.*

interface UserRepository : CrudRepository<User, UUID>
