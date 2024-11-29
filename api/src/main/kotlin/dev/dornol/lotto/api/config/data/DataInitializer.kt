package dev.dornol.lotto.api.config.data

import dev.dornol.lotto.api.app.service.lotto.NumberService
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class DataInitializer(
    private val numberService: NumberService,
) : ApplicationListener<ApplicationStartedEvent> {

    override fun onApplicationEvent(event: ApplicationStartedEvent) {
        numberService.initNumbers()
    }
}
