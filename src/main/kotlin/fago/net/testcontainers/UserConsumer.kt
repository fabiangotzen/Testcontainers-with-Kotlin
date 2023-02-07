package fago.net.testcontainers

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
