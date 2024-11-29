package dev.dornol.lotto.api.app.service.lotto

import dev.dornol.lotto.api.app.dto.lottoapi.DhLottoApiDrawResponse
import dev.dornol.lotto.api.app.dto.draw.DrawDto
import dev.dornol.lotto.api.app.repository.lotto.NumberRepository
import dev.dornol.lotto.api.app.repository.lotto.DrawNumberRepository
import dev.dornol.lotto.api.app.repository.lotto.DrawRepository
import dev.dornol.lotto.domain.entity.lotto.Draw
import dev.dornol.lotto.domain.entity.lotto.DrawNumber
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DrawService(
    private val drawRepository: DrawRepository,
    private val drawNumberRepository: DrawNumberRepository,
    private val numberRepository: NumberRepository
) {

    @Transactional
    fun finishLottoDraw(fetchedDraw: DhLottoApiDrawResponse) {
        val draw = drawRepository.findWithNumbersByDrawNo(fetchedDraw.drawNo!!) ?: Draw(fetchedDraw.drawNo)

        if (!draw.finish) {
            draw.setDrawFinishInfo(
                date = fetchedDraw.date!!,
                totalAmount = fetchedDraw.totalAmount!!,
                firstWinnerAmount = fetchedDraw.firstWinnerAmount!!,
                firstWinnerCount = fetchedDraw.firstWinnerCount!!,
            )

            drawRepository.save(draw)
            drawNumberRepository.saveAll(
                listOf(
                    DrawNumber(draw, numberRepository.getReferenceById(fetchedDraw.number1!!)),
                    DrawNumber(draw, numberRepository.getReferenceById(fetchedDraw.number2!!)),
                    DrawNumber(draw, numberRepository.getReferenceById(fetchedDraw.number3!!)),
                    DrawNumber(draw, numberRepository.getReferenceById(fetchedDraw.number4!!)),
                    DrawNumber(draw, numberRepository.getReferenceById(fetchedDraw.number5!!)),
                    DrawNumber(draw, numberRepository.getReferenceById(fetchedDraw.number6!!)),
                    DrawNumber(draw, numberRepository.getReferenceById(fetchedDraw.bonusNumber!!), true),
                )
            )
            addNewDraw(draw.drawNo + 1)
        }
    }

    @Transactional
    fun addNewDraw(drawNo: Long) {
        drawRepository.save(Draw(drawNo))
    }

    fun getLatestFinishedDraw() = drawRepository.findLatestFinishedDraw()

    fun getOngoingDraw(): DrawDto {
        val draw = drawRepository.findOngoingDraw() ?: throw EntityNotFoundException()
        return DrawDto(draw)
    }

    fun getLottoDraw(drawNo: Long): DrawDto {
        val draw = drawRepository.findWithNumbersByDrawNo(drawNo) ?: throw EntityNotFoundException()
        return DrawDto(draw)
    }

    fun getLatestFinishedWithNumbersDraw(): DrawDto {
        val draw = getLatestFinishedDraw() ?: throw EntityNotFoundException()
        draw.numbers.addAll(drawNumberRepository.findAllByDrawNo(draw.drawNo))
        return DrawDto(draw)
    }

}