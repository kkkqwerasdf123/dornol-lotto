package dev.dornol.lotto.api.config

import dev.dornol.lotto.api.config.security.RsaKeyProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(RsaKeyProperties::class)
class PropertyConfig {
}