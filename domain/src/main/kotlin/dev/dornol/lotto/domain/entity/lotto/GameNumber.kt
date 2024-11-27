package dev.dornol.lotto.domain.entity.lotto

import dev.dornol.lotto.domain.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "lotto_game_number")
class GameNumber(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "number_id", nullable = false, updatable = false)
    val number: Number? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id", nullable = false, updatable = false)
    val game: Game? = null,

    @Column(name = "bonus", nullable = false, updatable = false)
    val bonus: Boolean = false
) : BaseEntity() {
}