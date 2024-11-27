package dev.dornol.lotto.domain.entity.lotto

import dev.dornol.lotto.domain.entity.BaseCreationEntity
import jakarta.persistence.*

@Entity
@Table(name = "lotto_round_number")
class RoundNumber(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id", nullable = false, updatable = false)
    val round: Round,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "number_id", nullable = false, updatable = false)
    val number: Number,
    @Column(nullable = false, updatable = false)
    val bonus: Boolean = false
) : BaseCreationEntity() {
}