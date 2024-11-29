package dev.dornol.lotto.domain.entity.lotto

import dev.dornol.lotto.domain.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "lotto_draw")
class Draw(
    @Column(name = "draw_no", nullable = false, updatable = false, unique = true)
    val drawNo: Long
) : BaseEntity() {

    var date: LocalDate? = null
        protected set
    var totalAmount: Long = 0
        protected set
    var firstWinnerAmount: Long = 0
        protected set
    var firstWinnerCount: Long = 0
        protected set
    var finish: Boolean = false

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "draw")
    var numbers: MutableList<DrawNumber> = mutableListOf()

    fun setDrawFinishInfo(date: LocalDate?, totalAmount: Long, firstWinnerAmount: Long, firstWinnerCount: Long) {
        this.date = date
        this.totalAmount = totalAmount
        this.firstWinnerAmount = firstWinnerAmount
        this.firstWinnerCount = firstWinnerCount
        this.finish = true
    }

}