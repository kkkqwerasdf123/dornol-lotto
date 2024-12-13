package dev.dornol.lotto.api.config.security

import dev.dornol.lotto.api.config.security.handler.ApiAccessDeniedHandler
import dev.dornol.lotto.api.config.security.handler.ApiAuthenticationEntryPoint
import dev.dornol.lotto.api.config.security.handler.FormLoginAuthenticationFailureHandler
import dev.dornol.lotto.api.config.security.handler.FormLoginAuthenticationSuccessHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    @Value("\${dornol.security.cors.allowed-origins}")
    private val allowedOrigins: Array<String>,
    private val apiAccessDeniedHandler: ApiAccessDeniedHandler,
    private val apiAuthenticationEntryPoint: ApiAuthenticationEntryPoint,
    private val authenticationSuccessHandler: FormLoginAuthenticationSuccessHandler,
    private val authenticationFailureHandler: FormLoginAuthenticationFailureHandler,
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
        with(http) {
            csrf { it.disable() }
            headers { it.disable() }
            cors { it.configurationSource(corsConfigurationSource()) }
            authorizeHttpRequests {
                it.requestMatchers("/**").permitAll()
            }
            sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            exceptionHandling {
                it.accessDeniedHandler(apiAccessDeniedHandler)
                it.authenticationEntryPoint(apiAuthenticationEntryPoint)
            }
            formLogin {
                it.successHandler(authenticationSuccessHandler)
                it.failureHandler(authenticationFailureHandler)
            }
            build()
        }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()

        config.allowCredentials = true
        config.allowedOrigins = allowedOrigins.toMutableList()
        config.allowedMethods = mutableListOf(GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD).map { it.name() }
//        config.allowedHeaders = mutableListOf(AuthService.AUTHORIZATION_HEADER_NAME)
//        config.exposedHeaders = mutableListOf(AuthService.AUTHORIZATION_HEADER_NAME)
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        return source
    }

}