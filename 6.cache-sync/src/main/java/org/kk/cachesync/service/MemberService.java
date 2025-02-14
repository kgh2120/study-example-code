package org.kk.cachesync.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kk.cachesync.dto.CacheEvictMessage;
import org.kk.cachesync.dto.MemberDto;
import org.kk.cachesync.entity.Member;
import org.kk.cachesync.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final RedisMessagePublisher messagePublisher;
    @Value("${server.port}")
    private int port;


    @Cacheable(cacheNames = "user", key = "#id")
    public MemberDto findMemberById(Long id){
        log.info("cache Miss Member id : {}", id);
        Member member = memberRepository.findById(id)
                .orElseThrow();
        return new MemberDto(member, port);
    }
    @CacheEvict(cacheNames = "user", key = "#id")
    public void updateStatueMessage(Long id, String message){
        Member member = memberRepository.findById(id)
                .orElseThrow();
        member.updateStatusMessage(message);

        messagePublisher.publishCacheEvictMessage(new CacheEvictMessage("user", id));
    }


}
