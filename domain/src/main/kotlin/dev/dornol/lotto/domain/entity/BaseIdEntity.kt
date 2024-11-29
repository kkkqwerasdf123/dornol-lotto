package dev.dornol.lotto.domain.entity

import dev.dornol.lotto.domain.generator.SnowFlakeGeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.domain.Persistable

@MappedSuperclass
abstract class BaseIdEntity(
    @Id
    @SnowFlakeGeneratedValue
    val id: Long = 0L
) : Persistable<Long> {

    override fun getId() = id

    override fun isNew() = id == 0L

}