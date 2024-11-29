package dev.dornol.lotto.api.app.dto.draw

import dev.dornol.lotto.domain.entity.lotto.Draw
import java.time.LocalDate

data class DrawDto(
    val id: Long,
    val drawNo: Long,
    val date: LocalDate?,
    val numbers: List<DrawNumberDto> = listOf(),
) {
    constructor(draw: Draw) : this(
        draw.id,
        draw.drawNo,
        draw.date,
        draw.takeIf { it.finish }?.run { numbers.map { DrawNumberDto(it.number.value, it.bonus) } }
            ?: listOf()
    )
}