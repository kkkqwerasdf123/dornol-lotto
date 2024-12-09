package dev.dornol.lotto.domain.entity.lotto.domain.generator

import dev.dornol.lotto.domain.generator.SnowFlakeIdGenerator
import org.assertj.core.api.WithAssertions
import org.junit.jupiter.api.Test
import java.time.Instant
import java.util.*
import java.util.concurrent.Executors
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
        val list = Collections.synchronizedList(mutableListOf<Long>())
        val generator = SnowFlakeIdGenerator(1L)

        //when

        val exe = Executors.newVirtualThreadPerTaskExecutor()
        exe.use {
            for (i in 0..10000) {
                it.submit {
                    list.add(generator.generate(null, null) as Long)
                }
            }
        }

        //then
        println("list : ${list.size}")
        println("set : ${list.toSet().size}")
        assertThat(list).doesNotHaveDuplicates()
    }

    @Test
    fun `generate() multiple nodes`() {
        //given
        val list = Collections.synchronizedList(mutableListOf<Long>())

        //when
        val exe = Executors.newVirtualThreadPerTaskExecutor()

        exe.use {
            for (i in 0..<10) {
                val generator = SnowFlakeIdGenerator(i.toLong())
                for (j in 0..1000) {
                    it.submit {
                        list.add(generator.generate(null, null) as Long)
                    }

                }
            }
        }

        //then
        println("list : ${list.size}")
        println("set : ${list.toSet().size}")
        assertThat(list).doesNotHaveDuplicates()
    }

}