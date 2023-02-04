package fago.net.testcontainerexample

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UserProducer @Autowired constructor(private val kafkaTemplate: KafkaTemplate<String?, User?>) {

    fun send(){
        val user = User("TestUser", 10)
        this.kafkaTemplate.send("userTopic", user)
    }

}
