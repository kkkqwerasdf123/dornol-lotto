package dev.dornol.lotto.domain.entity.lotto

import dev.dornol.lotto.domain.entity.BaseCreationEntity
import jakarta.persistence.*

@Entity
@Table(name = "lotto_draw_number")
class DrawNumber(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "draw_id", nullable = false, updatable = false)
    val draw: Draw,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "number_id", nullable = false, updatable = false)
    val number: Number,
    @Column(nullable = false, updatable = false)
    val bonus: Boolean = false
) : BaseCreationEntity() {
}