package dev.dornol.lotto.domain.entity.member

import dev.dornol.lotto.domain.entity.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    name: String
) : BaseEntity() {

    @Column(name = "name", nullable = false)
    var name: String = name
        protected set
}