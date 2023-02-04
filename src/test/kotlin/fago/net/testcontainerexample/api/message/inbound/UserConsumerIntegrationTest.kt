package fago.net.testcontainerexample.api.message.inbound

import fago.net.testcontainerexample.domain.UserDto
import fago.net.testcontainerexample.infrastructure.UserRepository
import junit.framework.TestCase.assertEquals
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.junit.Test
import org.junit.jupiter.api.assertAll
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName
import java.util.*
@SpringBootTest
@Testcontainers
class UserConsumerIntegrationTest {

    companion object {
        @JvmStatic
        @Container
        val kafka = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
    }

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun testConsumeUserMessage() {
        // arrange
        kafka.start()
        val KAFKA_BOOTSTRAP_SERVER = kafka.bootstrapServers

        val producerProps = mapOf(
            "bootstrap.servers" to KAFKA_BOOTSTRAP_SERVER,
        )

        val userDto = UserDto(UUID.randomUUID(), "TestUser", 10);

        // act
        KafkaProducer<String, UserDto>(producerProps).send(ProducerRecord("test", userDto))
        this.userRepository.findById(userDto.id)

        // assert
        assertAll("Assert that User was successfully saved",
            { assertEquals("TestUser", userDto.name) },
            { assertEquals(10, userDto.age) }
        )
    }
}
