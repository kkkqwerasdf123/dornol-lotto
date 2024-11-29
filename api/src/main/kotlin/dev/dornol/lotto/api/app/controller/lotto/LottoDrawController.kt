package dev.dornol.lotto.api.app.controller.lotto

import dev.dornol.lotto.api.app.service.lotto.DrawService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lotto/draws")
class LottoDrawController(
    private val drawService: DrawService
) {

    @GetMapping("/{drawNo}")
    fun getDraw(@PathVariable drawNo: Long) = drawService.getLottoDraw(drawNo)

    @GetMapping("/last-finished")
    fun getLastFinishedDraw() = drawService.getLatestFinishedWithNumbersDraw()

    @GetMapping("/ongoing")
    fun getOngoingDraw() = drawService.getOngoingDraw()

}