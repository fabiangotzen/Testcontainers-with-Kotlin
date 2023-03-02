package fago.net.testcontainers

import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.testcontainers.containers.KafkaContainer
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

class TestcontainersInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
    companion object {
        val kafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
        val postgresContainer = PostgreSQLContainer(DockerImageName.parse("postgres:12"))
    }

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
