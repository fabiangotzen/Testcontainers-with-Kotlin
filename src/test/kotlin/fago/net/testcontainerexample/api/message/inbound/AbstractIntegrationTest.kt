package fago.net.testcontainerexample.api.message.inbound

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.utility.DockerImageName
import org.testcontainers.containers.PostgreSQLContainer;
open class AbstractIntegrationTest {
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
