package dev.dornol.lotto.domain.entity.lotto

import dev.dornol.lotto.domain.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "lotto_game")
class Game : BaseEntity() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "draw_id", nullable = false, updatable = false)
    val draw: Draw? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "game")
    val numbers: List<GameNumber> = ArrayList()

}