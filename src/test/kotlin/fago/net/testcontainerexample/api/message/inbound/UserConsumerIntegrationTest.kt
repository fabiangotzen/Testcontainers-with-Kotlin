package fago.net.testcontainerexample.api.message.inbound

import fago.net.testcontainerexample.UserProducer
import fago.net.testcontainerexample.domain.UserDto
import fago.net.testcontainerexample.infrastructure.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@SpringBootTest
@ActiveProfiles("test")
class UserConsumerIntegrationTest

 @Autowired constructor(
    val userRepository: UserRepository,
    val userProducer: UserProducer,
) {

    @org.junit.jupiter.api.Test
    fun testConsumeUserMessage() {
        // arrange
        val userDto = UserDto(UUID.randomUUID(), "TestUser", 10);

        // act
        this.userProducer.send(userDto)
        this.userRepository.findById(userDto.id)

        // assert
        assertAll("Assert that User was successfully saved",
            { Assertions.assertEquals("TestUser", userDto.name) },
            { Assertions.assertEquals(10, userDto.age) }
        )
    }
}
