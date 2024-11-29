package dev.dornol.lotto.api.app.task

import dev.dornol.lotto.api.app.service.lotto.DrawService
import dev.dornol.lotto.api.app.service.lottoapi.LottoApiService
import dev.dornol.lotto.api.app.util.DistributedLockProvider
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime

private val log = KotlinLogging.logger {}

@Component
class FinishDrawScheduler(
    private val lottoApiService: LottoApiService,
    private val drawService: DrawService,
    private val lockProvider: DistributedLockProvider
) {
    private var nextDrawDateTime: LocalDateTime? = null

    @Scheduled(fixedDelay = 1_000)
    fun fetchNextDraw() {
        if (nextDrawDateTime == null || LocalDateTime.now().isAfter(nextDrawDateTime)) {
            val draw = drawService.getLatestFinishedDraw()
            nextDrawDateTime = draw?.date?.plusDays(7)?.atTime(20, 40)

            val drawNo = (draw?.drawNo ?: 0) + 1

            lottoApiService.fetchLottoDraw(drawNo)
                .takeIf { it.returnValue.equals("success", true) }
                ?.run { lockProvider.withLock("saveLottoDraw-$drawNo") { drawService.finishLottoDraw(this) } }
        }
    }

}