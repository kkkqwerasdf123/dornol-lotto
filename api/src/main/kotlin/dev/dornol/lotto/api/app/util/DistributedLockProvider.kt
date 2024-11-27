package dev.dornol.lotto.api.app.util

import io.github.oshai.kotlinlogging.KotlinLogging
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit

private val log = KotlinLogging.logger {}

@Service
class DistributedLockProvider(
    private val redissonClient: RedissonClient
) {

    fun withLock(key: String, waitTime: Long = 5000, leaseTime: Long = 5000, logic: () -> Unit) {
        val lock = redissonClient.getLock(key)
        try {
            val lockable = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS)
            log.debug { "lock: $lockable" }
            if (!lockable) {
                log.error { "failed to obtain lock $key" }
                throw RuntimeException("failed to obtain lock $key")
            }
            logic()
        } finally {
            lock.unlock()
            log.debug { "unlock: $lock" }
        }
    }

}