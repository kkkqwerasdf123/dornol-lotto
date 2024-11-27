package dev.dornol.lotto.domain.generator

import io.github.oshai.kotlinlogging.KotlinLogging
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.id.IdentifierGenerator
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

private val log = KotlinLogging.logger {}

@Component
class SnowFlakeIdGenerator(
    private val nodeId: Long = 1L,
) : IdentifierGenerator {
    private val nodeIdBits: Int = 10
    private val sequenceBits: Int = 12
    private val epochBits: Int = 64 - 1 - nodeIdBits - sequenceBits
    private val epoch: Long = LocalDateTime.of(2024, 1, 1, 0, 0)
        .atZone(ZoneId.of("Asia/Seoul"))
        .toInstant()
        .toEpochMilli()

    private val maxNodeId = (1L shl nodeIdBits) - 1
    private val maxSequence = (1L shl sequenceBits) - 1
    private val maxEpoch = (1L shl epochBits) - 1

    private var sequence = 0L
    private var lastTimestamp = -1L

    init {
        require(nodeId in 0..maxNodeId) { "nodeId must be between 0 and $maxNodeId" }
    }

    @Synchronized
    fun generateId(): Long {
        var timestamp = currentTimestamp()

        require(timestamp >= lastTimestamp) { "failed to generate id. Clock moved backwards." }
        require(timestamp <= maxEpoch) { "failed to generate id" }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) and maxSequence
            if (sequence == 0L) {
                while (timestamp == lastTimestamp) {
                    Thread.sleep(1)
                    timestamp = currentTimestamp()
                }
            }
        } else {
            sequence = 0L
        }

        lastTimestamp = timestamp

        log.info { "generating... $timestamp" }

        return ((timestamp - epoch) shl (nodeIdBits + sequenceBits)) or
                (nodeId shl sequenceBits) or
                sequence
    }

    private fun currentTimestamp(): Long = Instant.now().toEpochMilli()

    override fun generate(p0: SharedSessionContractImplementor?, p1: Any?): Any {
        return this.generateId()
    }
}