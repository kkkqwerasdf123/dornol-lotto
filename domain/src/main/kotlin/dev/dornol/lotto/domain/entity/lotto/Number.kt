package dev.dornol.lotto.domain.entity.lotto

import dev.dornol.lotto.domain.entity.BaseCreationEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.validator.constraints.Range

@Entity
@Table(name = "lotto_number")
class Number(
    @Id
    @Column(name = "number", updatable = false, nullable = false)
    @Range(min = 1, max = 45)
    val number: Int
)