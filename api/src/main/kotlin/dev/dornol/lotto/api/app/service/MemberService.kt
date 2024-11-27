package dev.dornol.lotto.api.app.service

import dev.dornol.lotto.api.app.repository.MemberRepository
import dev.dornol.lotto.domain.entity.member.Member
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    fun getMembers() : List<Member> = memberRepository.findAll()
}
