package dev.dornol.lotto.api.app.dto.draw

data class DrawNumberDto(
    val number: Int,
    val isBonus: Boolean = false
)