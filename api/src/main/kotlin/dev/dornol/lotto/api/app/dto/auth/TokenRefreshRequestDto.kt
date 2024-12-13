package dev.dornol.lotto.api.app.dto.auth

data class TokenRefreshRequestDto(
    val key: String,
    val refreshToken: String
)