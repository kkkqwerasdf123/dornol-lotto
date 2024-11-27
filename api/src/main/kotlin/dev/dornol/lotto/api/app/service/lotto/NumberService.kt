package dev.dornol.lotto.api.app.service.lotto

import dev.dornol.lotto.api.app.repository.lotto.NumberRepository
import dev.dornol.lotto.domain.entity.lotto.Number
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class NumberService(
    private val numberRepository: NumberRepository
) {

    @Transactional
    fun initNumbers() {
        val numbers = numberRepository.findAllNumbers().map { it.number }
        if (numbers.size != 45) {
            numberRepository.saveAll((1..45).map { Number(it) })
        }
    }

}