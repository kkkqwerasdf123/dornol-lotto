package dev.dornol.lotto.api.app.dto.auth

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
)