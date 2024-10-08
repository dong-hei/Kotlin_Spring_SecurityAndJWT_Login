package com.example.demo.common.service

import com.example.demo.common.dto.CustomUser
import com.example.demo.member.entity.Member
import com.example.demo.member.repository.MemberRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
/**
 * 유저상세정보를 구현
 */
@Service
class CustomUserDetailsService(
     private val memberRepository: MemberRepository,
     private val passwordEncoder: PasswordEncoder
) : UserDetailsService{
     override fun loadUserByUsername(username: String): UserDetails =
          memberRepository.findByLoginId(username) // 로그인 아이디 기준으로 찾는다
               // 조회값이 null일때
               ?. let { createUserDetails(it)} ?: throw UsernameNotFoundException("해당 유저는 존재하지 않습니다.")

     //조회값이 null이 아닐때
     private fun createUserDetails(member: Member): UserDetails =
          CustomUser(
               member.id!!,
               member.loginId,
               passwordEncoder.encode(member.password),
               member.memberRole!!.map { SimpleGrantedAuthority("ROLE_${it.role}")}
     )

}
