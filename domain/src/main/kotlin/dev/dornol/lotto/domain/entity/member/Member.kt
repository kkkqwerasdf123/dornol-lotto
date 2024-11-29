package dev.dornol.lotto.domain.entity.member

import dev.dornol.lotto.domain.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "member")
class Member(
    name: String,
    username: String,
    password: String? = null
) : BaseEntity() {

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set

    @Column(name = "username", length = 20, unique = true, nullable = false, updatable = false)
    val username: String = username

    @Column(name = "password", length = 255)
    var password: String? = password
}