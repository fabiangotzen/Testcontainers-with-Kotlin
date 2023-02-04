package fago.net.testcontainerexample.api.message.inbound

import fago.net.testcontainerexample.domain.UserDto
import fago.net.testcontainerexample.service.UserService
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@KafkaListener(id = "myId", topics = ["userTopic"])
@Component
class UserConsumer(private val userService: UserService) {

    @Bean
    fun topic() = NewTopic("userTopic", 10, 1)

    @KafkaHandler
    fun listen(userDto: UserDto) {
        println(userDto)
        userService.save(userDto);
    }

}
