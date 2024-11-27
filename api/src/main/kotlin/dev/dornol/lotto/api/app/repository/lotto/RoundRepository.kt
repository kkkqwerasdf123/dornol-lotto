package dev.dornol.lotto.api.app.repository.lotto

import dev.dornol.lotto.domain.entity.lotto.Round
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RoundRepository : JpaRepository<Round, Long> {

    fun findByRoundNo(round: Long): Round?

    @Query("""
        select r
        from Round r
        where r.deleted = false
        order by r.roundNo desc
        limit 1
    """)
    fun findLatestRound(): Round?

    @Query("""
        select r
        from Round r
        where r.finish = true
          and r.deleted = false
        order by r.roundNo desc
        limit 1
    """)
    fun findLatestFinishedRound(): Round?

}