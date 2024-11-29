package dev.dornol.lotto.api.app.repository.lotto

import dev.dornol.lotto.domain.entity.lotto.Number
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface NumberRepository : JpaRepository<Number, Int> {

    @Query(
        """
        select n 
        from Number n
        where n.value >= 1 and n.value <= 45
        order by n.value
    """
    )
    fun findAllNumbers(): List<Number>

}