package dev.dornol.lotto.domain.entity

import dev.dornol.lotto.domain.generator.SnowFlakeGenerated
import jakarta.persistence.Embeddable
import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Embeddable
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseCreationEntity {

    @Id
    @SnowFlakeGenerated("snowFlakeGenerated")
    val id: Long? = null

    @CreatedBy
    var createdBy: Long? = null
        protected set

    @LastModifiedBy
    var lastModifiedBy: Long? = null
        protected set

    @CreatedDate
    var createdDate: LocalDateTime? = null
        protected set

    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null
        protected set
}