package dev.dornol.lotto.api.app.util

import dev.dornol.lotto.domain.generator.SnowFlakeIdGenerator
import org.junit.jupiter.api.Test

class SnowFlakeIdGeneratorTest {
    
    @Test
    fun `생성 테스트`() {
        //given
        val generator = SnowFlakeIdGenerator(1L)

        //when
        val list = (0..10000).map {
            generator.generateId()
        }

        //then
        list.forEach {
            println("${it} / ${java.lang.Long.toBinaryString(it)}")
        }
    }
    
}