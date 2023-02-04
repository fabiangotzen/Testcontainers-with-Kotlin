package fago.net.testcontainerexample.api.message.inbound

import fago.net.testcontainerexample.UserProducer
import fago.net.testcontainerexample.domain.UserDto
import fago.net.testcontainerexample.infrastructure.UserRepository
import org.awaitility.kotlin.untilNotNull
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import java.util.logging.Logger


class UserConsumerIntegrationTest(@Autowired userRepository: UserRepository, @Autowired userProducer: UserProducer) : AbstractIntegrationTest(userRepository, userProducer) {

    val LOG = Logger.getLogger(this.javaClass.name)

    @org.junit.jupiter.api.Test
    fun testConsumeUserMessage() {
        // arrange
        val userDto = UserDto(UUID.randomUUID(), "TestUser", 10);

        // act
        this.userProducer.send(userDto)
        LOG.info("Sending message with user: " + userDto.name)

        org.awaitility.kotlin.await untilNotNull { this.userRepository.findById(userDto.id).get() }
        val persistedUser = this.userRepository.findById(userDto.id).get()

        // assert
        assertAll("Assert that User was successfully saved",
            { Assertions.assertEquals(persistedUser.name, userDto.name) },
            { Assertions.assertEquals(persistedUser.age, userDto.age) }
        )
    }
}
