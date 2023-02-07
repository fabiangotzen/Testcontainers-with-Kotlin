package fago.net.testcontainers

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.KafkaTemplate
import java.util.*


@SpringBootApplication
@EnableKafka
class TestcontainersApplication {

    @Bean
    fun runner(template: KafkaTemplate<String?, UserDto?>) =
        ApplicationRunner { template.send("userTopic", UserDto(UUID.randomUUID(),"testUser", 10)) }

}
fun main() {
    runApplication<TestcontainersApplication>()
}
