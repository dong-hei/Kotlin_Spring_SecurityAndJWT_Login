package com.example.demo.member.repository

import com.example.demo.member.entity.Member
import com.example.demo.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?

}

interface MemberRoleRepository : JpaRepository<MemberRole, Long>