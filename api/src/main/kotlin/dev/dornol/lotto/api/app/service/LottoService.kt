package dev.dornol.lotto.api.app.service

import dev.dornol.lotto.api.app.repository.lotto.NumberRepository
import dev.dornol.lotto.api.app.repository.lotto.RoundRepository
import org.springframework.stereotype.Service

@Service
class LottoService(
    private val roundRepository: RoundRepository,
    private val numberRepository: NumberRepository,
) {

    fun getLottoRound(roundNo: Long) = roundRepository.findByRoundNo(roundNo)

    fun getNumbers() = numberRepository.findAll()

    fun getLottoLatestFinishedRound() =
        roundRepository.findLatestFinishedRound() ?: throw RuntimeException()


}
