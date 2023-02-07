package fago.net.testcontainers

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

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
