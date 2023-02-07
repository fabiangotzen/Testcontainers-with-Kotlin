package fago.net.testcontainers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(initializers = [AbstractIntegrationTest.Initializer::class])
abstract class AbstractIntegrationTest @Autowired constructor(
    val userRepository: UserRepository,
    val userProducer: UserProducer,
) {
    companion object {
        val kafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
        val postgresContainer = PostgreSQLContainer(DockerImageName.parse("postgres:12"))
    }

    internal class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            kafkaContainer.start()
            postgresContainer.start()

            TestPropertyValues.of(
                "spring.kafka.bootstrap-servers=${kafkaContainer.bootstrapServers}",
                "spring.datasource.url=${postgresContainer.jdbcUrl}",
                "spring.datasource.username=${postgresContainer.username}",
                "spring.datasource.password=${postgresContainer.password}"
            ).applyTo(configurableApplicationContext.environment)
        }
    }
}
