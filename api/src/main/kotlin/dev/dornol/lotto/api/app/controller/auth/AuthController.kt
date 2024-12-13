package dev.dornol.lotto.api.app.controller.auth

import dev.dornol.lotto.api.app.service.auth.TokenService
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val tokenService: TokenService,
) {
    @PostMapping("/token")
    fun token(authentication: Authentication): String {
        log.debug { "Token requested for user: '${authentication.name}'" }
        val token = tokenService.generateToken(authentication)
        log.debug { "Token generated: '${token}'" }
        return token
    }

}