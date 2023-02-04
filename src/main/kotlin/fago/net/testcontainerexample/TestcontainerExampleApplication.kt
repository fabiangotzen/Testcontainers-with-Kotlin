package fago.net.testcontainerexample

import fago.net.testcontainerexample.domain.User
import fago.net.testcontainerexample.domain.UserDto
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import java.util.*


@SpringBootApplication
@EnableKafka
class TestcontainerExampleApplication {

    @Bean
    fun runner(template: KafkaTemplate<String?, UserDto?>) =
        ApplicationRunner { template.send("userTopic", UserDto(UUID.randomUUID(),"testUser", 10)) }

}
fun main() {
    runApplication<TestcontainerExampleApplication>()
}
