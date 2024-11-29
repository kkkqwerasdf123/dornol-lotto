package dev.dornol.lotto.api.app.repository.lotto

import dev.dornol.lotto.domain.entity.lotto.Draw
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DrawRepository : JpaRepository<Draw, Long> {

    @Query(
        """
        select r
        from Draw r
            left outer join fetch r.numbers
        where r.drawNo = :drawNo
          and r.deleted = false
    """
    )
    fun findWithNumbersByDrawNo(@Param("drawNo") drawNo: Long): Draw?

    @Query(
        """
        select r
        from Draw r
        where r.deleted = false
          and r.finish = false
    """
    )
    fun findOngoingDraw(): Draw?

    @Query(
        """
        select r
        from Draw r
        where r.finish = true
          and r.deleted = false
        order by r.drawNo desc
        limit 1
    """
    )
    fun findLatestFinishedDraw(): Draw?

}