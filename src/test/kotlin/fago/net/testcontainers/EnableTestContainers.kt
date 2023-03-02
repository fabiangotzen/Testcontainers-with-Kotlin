package fago.net.testcontainers

import org.springframework.test.context.ContextConfiguration


@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ContextConfiguration(initializers = [TestcontainersInitializer::class])
annotation class EnableTestcontainers
