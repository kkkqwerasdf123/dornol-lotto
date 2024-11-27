package dev.dornol.lotto.api.app.repository

import dev.dornol.lotto.domain.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
}