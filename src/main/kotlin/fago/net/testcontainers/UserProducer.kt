package fago.net.testcontainers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class UserProducer @Autowired constructor(private val kafkaTemplate: KafkaTemplate<String?, UserDto?>) {

    fun send(userDto: UserDto){
        this.kafkaTemplate.send("userTopic", userDto)
    }

}
