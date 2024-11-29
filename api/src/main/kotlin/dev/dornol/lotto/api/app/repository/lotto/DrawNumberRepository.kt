package dev.dornol.lotto.api.app.repository.lotto

import dev.dornol.lotto.domain.entity.lotto.DrawNumber
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface DrawNumberRepository : JpaRepository<DrawNumber, Long> {

    @Query(
        """
        select n 
        from DrawNumber n
        where n.draw.drawNo = :drawNo
        order by n.number.value
    """
    )
    fun findAllByDrawNo(@Param("drawNo") drawNo: Long): List<DrawNumber>

}