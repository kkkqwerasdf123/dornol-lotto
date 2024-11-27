package dev.dornol.lotto.api.app.controller.lotto

import dev.dornol.lotto.api.app.service.LottoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lotto/rounds")
class LottoRoundController(
    private val lottoService: LottoService
) {

    @GetMapping("/current")
    fun getCurrentRound() {
//        lottoService.getLottoRound()
    }

}