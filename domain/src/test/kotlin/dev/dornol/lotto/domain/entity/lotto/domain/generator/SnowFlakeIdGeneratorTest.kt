package dev.dornol.lotto.domain.entity.lotto.domain.generator

import dev.dornol.lotto.domain.generator.SnowFlakeIdGenerator
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.random.Random

class SnowFlakeIdGeneratorTest : WithAssertions {

    @Test
    fun `generate() nodeId`() {
        //given
        val nodeId = Random.nextLong(SnowFlakeIdGenerator.MAX_NODE_ID + 1)
        val generator = SnowFlakeIdGenerator(nodeId)

        //when
        val result = generator.generate(null, null) as Long

        //then
        assertThat((result shr SnowFlakeIdGenerator.SEQUENCE_BITS) and nodeId).isEqualTo(nodeId)
    }

    @Test
    fun `generate() timestamp`() {
        //given
        val generator = SnowFlakeIdGenerator(1L)

        //when
        val result = generator.generate(null, null) as Long

        //then
        val timestamp = (result and -1) shr (SnowFlakeIdGenerator.SEQUENCE_BITS + SnowFlakeIdGenerator.NODE_ID_BITS)
        val expected = Instant.now().toEpochMilli() - SnowFlakeIdGenerator.epoch

        assertThat(timestamp).isCloseTo(expected, within(1000L))
    }

    @Test
    fun `generate() single thread`() {
        //given
        val generator = SnowFlakeIdGenerator(1L)

        //when
        val result = (1..10000).map { generator.generate(null, null) as Long }

        //then
        assertThat(result).doesNotHaveDuplicates()
    }

    @Test
    fun `generate() multiple threads`() {
        //given
        val list = mutableListOf<Long>()
        val generator = SnowFlakeIdGenerator(1L)

        //when
        val threads = (0..<10).map {
            Thread { list.addAll((0..<10000).map { generator.generate(null, null) as Long }) }
        }
        threads.forEach { it.start(); it.join() }

        //then
        assertThat(list).doesNotHaveDuplicates()
    }

    @Test
    fun `generate() multiple nodes`() {
        //given
        val list = mutableListOf<Long>()

        //when
        val threads = (0..<10).map {
            Thread {
                val generator = SnowFlakeIdGenerator(it.toLong())
                list.addAll((0..<10000).map { generator.generate(null, null) as Long })
            }
        }

        threads.forEach { it.start(); it.join() }

        //then
        assertThat(list).doesNotHaveDuplicates()
    }


}