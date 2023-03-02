package fago.net.testcontainers

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@EnableTestcontainers
@SpringBootTest
@ActiveProfiles("test")
annotation class IntegrationTest(
    vararg val properties: String = []
)
