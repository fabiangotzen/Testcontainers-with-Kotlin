package fago.net.testcontainerexample

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@KafkaListener(id = "myId", topics = ["userTopic"])
@Service
class UserConsumer {

    @Bean
    fun topic() = NewTopic("userTopic", 10, 1)

    @KafkaHandler
    fun listen(user: User?) {
        println(user)
    }

}
