package dev.dornol.lotto.domain.entity.lotto

import dev.dornol.lotto.domain.entity.BaseEntity
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "lotto_round")
class Round(
    @Column(name = "round_no", nullable = false, updatable = false, unique = true)
    val roundNo: Long
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

    fun setRoundInfo(date: LocalDate?, totalAmount: Long, firstWinnerAmount: Long, firstWinnerCount: Long) {
        this.date = date
        this.totalAmount = totalAmount
        this.firstWinnerAmount = firstWinnerAmount
        this.firstWinnerCount = firstWinnerCount
        this.finish = true
    }

}