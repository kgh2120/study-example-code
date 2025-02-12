package org.kk.cachesync.controller;

import lombok.RequiredArgsConstructor;
import org.kk.cachesync.dto.MemberDto;
import org.kk.cachesync.dto.MemberStatusMessageUpdateDto;
import org.kk.cachesync.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/member/{id}")
    public MemberDto findById(@PathVariable Long id) {
        return memberService.findMemberById(id);
    }

    @PatchMapping("/member/{id}")
    public void findById(@PathVariable Long id, @RequestBody MemberStatusMessageUpdateDto memberStatusMessageUpdateDto) {
        memberService.updateStatueMessage(id, memberStatusMessageUpdateDto.getMessage());
    }

}
