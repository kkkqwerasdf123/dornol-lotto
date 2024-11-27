package dev.dornol.lotto.api.app.service.lotto

import dev.dornol.lotto.api.app.dto.LottoApiRoundResponse
import dev.dornol.lotto.api.app.repository.lotto.NumberRepository
import dev.dornol.lotto.api.app.repository.lotto.RoundNumberRepository
import dev.dornol.lotto.api.app.repository.lotto.RoundRepository
import dev.dornol.lotto.domain.entity.lotto.Round
import dev.dornol.lotto.domain.entity.lotto.RoundNumber
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RoundService(
    private val roundRepository: RoundRepository,
    private val roundNumberRepository: RoundNumberRepository,
    private val numberRepository: NumberRepository
) {

    @Transactional
    fun saveLottoRound(fetchedRound: LottoApiRoundResponse) {
        val round = roundRepository.findByRoundNo(fetchedRound.roundNo!!) ?: Round(fetchedRound.roundNo)

        if (!round.finish) {
            round.setRoundInfo(
                date = fetchedRound.date!!,
                totalAmount = fetchedRound.totalAmount!!,
                firstWinnerAmount = fetchedRound.firstWinnerAmount!!,
                firstWinnerCount = fetchedRound.firstWinnerCount!!,
            )

            roundRepository.save(round)
            roundNumberRepository.saveAll(
                listOf(
                    RoundNumber(round, numberRepository.getReferenceById(fetchedRound.number1!!)),
                    RoundNumber(round, numberRepository.getReferenceById(fetchedRound.number2!!)),
                    RoundNumber(round, numberRepository.getReferenceById(fetchedRound.number3!!)),
                    RoundNumber(round, numberRepository.getReferenceById(fetchedRound.number4!!)),
                    RoundNumber(round, numberRepository.getReferenceById(fetchedRound.number5!!)),
                    RoundNumber(round, numberRepository.getReferenceById(fetchedRound.number6!!)),
                    RoundNumber(round, numberRepository.getReferenceById(fetchedRound.bonusNumber!!), true),
                )
            )
        }
    }

    fun findLatestFinishedRound() = roundRepository.findLatestFinishedRound()

}