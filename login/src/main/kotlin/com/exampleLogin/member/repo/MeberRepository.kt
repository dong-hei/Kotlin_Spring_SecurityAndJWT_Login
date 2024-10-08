package com.exampleLogin.member.repo

import com.exampleLogin.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByLoginId(loginId: String): Member?
}