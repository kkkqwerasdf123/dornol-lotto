package dev.dornol.lotto.api.config.data

import dev.dornol.lotto.api.app.service.LottoApiService
import dev.dornol.lotto.api.app.service.lotto.NumberService
import dev.dornol.lotto.api.app.service.lotto.RoundService
import dev.dornol.lotto.api.app.util.DistributedLockProvider
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val lottoApiService: LottoApiService,
    private val numberService: NumberService,
    private val roundService: RoundService,
    private val lockProvider: DistributedLockProvider
) : ApplicationListener<ApplicationStartedEvent> {

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        numberService.initNumbers()
        initRounds()
    }

    private fun initRounds() {
        val latestRound = roundService.findLatestFinishedRound()
        var roundNo = latestRound?.roundNo ?: 0

        while (true) {
            lottoApiService.getLottoByRoundNo(++roundNo)
                .takeIf { it.returnValue.equals("success", true) }
                ?.run { lockProvider.withLock("saveLottoRound-$roundNo") { roundService.saveLottoRound(this) } }
                ?: break;
            Thread.sleep(500)
        }
    }
}