package dev.dornol.lotto.api

import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneId

class BasicTest {

    @Test
    fun `test`() {
        val instant = LocalDateTime.of(2024, 11, 26, 20, 40).atZone(ZoneId.of("Asia/Seoul")).toInstant()

        println(System.currentTimeMillis())
        println(instant.toEpochMilli())

    }

}