package dev.dornol.lotto.api.app.controller

import dev.dornol.lotto.api.app.service.LottoService
import dev.dornol.lotto.api.app.service.MemberService
import dev.dornol.lotto.domain.entity.lotto.Number
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class MemberController(
    private val memberService: MemberService,
    private val lottoService: LottoService
) {

    @GetMapping
    fun getMember(): List<String> = memberService.getMembers().map { it.name }

    @GetMapping("/round/{roundNo}")
    fun getLotto(@PathVariable roundNo: Long) = lottoService.getLottoRound(roundNo)

    @GetMapping("/numbers")
    fun getNumber(): MutableList<Number> = lottoService.getNumbers()

}