package fago.net.testcontainers

import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import java.util.*


@SpringBootApplication
@EnableKafka
class TestcontainersApplication {

    @Bean
    fun runner(producer: UserProducer) =
        ApplicationRunner { producer.send(UserDto(UUID.randomUUID(),"testUser", 10)) }

}
fun main() {
    runApplication<TestcontainersApplication>()
}
