package fago.net.testcontainerexample.service

import fago.net.testcontainerexample.domain.User
import fago.net.testcontainerexample.domain.UserDto
import fago.net.testcontainerexample.infrastructure.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
@Transactional
class UserService(private val userRepository: UserRepository) {

    fun save(userDto: UserDto): User {
        return userRepository.save(convertToEntity(userDto));
    }

    fun convertToEntity(user: UserDto): User {
        return User(
            id = user.id,
            name = user.name,
            age = user.age,
        )
    }

}
