package fago.net.testcontainerexample

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate

@SpringBootApplication
@EnableKafka
class TestcontainerExampleApplication{

@Bean
fun topic() = NewTopic("topic1", 10, 1)

@Bean
fun runner(template: KafkaTemplate<String?, String?>) =
    ApplicationRunner { template.send("topic1", "test") }

}

fun main() {
}
