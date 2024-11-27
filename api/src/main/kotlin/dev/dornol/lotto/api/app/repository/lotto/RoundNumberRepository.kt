package dev.dornol.lotto.api.app.repository.lotto

import dev.dornol.lotto.domain.entity.lotto.RoundNumber
import org.springframework.data.jpa.repository.JpaRepository

interface RoundNumberRepository : JpaRepository<RoundNumber, Long> {
}