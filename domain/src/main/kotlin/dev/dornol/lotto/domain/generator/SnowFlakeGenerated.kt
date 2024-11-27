package dev.dornol.lotto.domain.generator

import org.hibernate.annotations.IdGeneratorType
import org.hibernate.id.enhanced.Optimizer
import kotlin.reflect.KClass

@IdGeneratorType(SnowFlakeIdGenerator::class)
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class SnowFlakeGenerated(
    val name: String,
    val startWith: Int = 1,
    val incrementBy: Int = 50,
    val optimizer: KClass<out Optimizer> = Optimizer::class
)