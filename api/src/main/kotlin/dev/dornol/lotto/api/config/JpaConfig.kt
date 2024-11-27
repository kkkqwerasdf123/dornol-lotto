package dev.dornol.lotto.api.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration

@Configuration
@EntityScan(basePackages = ["dev.dornol.lotto.domain.entity"])
class JpaConfig {
}