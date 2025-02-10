package org.kk.cachesync.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.kk.cachesync.dto.MemberDto;
import org.kk.cachesync.entity.Member;
import org.kk.cachesync.repository.MemberRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    @Cacheable(cacheNames = "user", key = "#id")
    public MemberDto findMemberById(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow();
        return new MemberDto(member);
    }


}
