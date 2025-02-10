package org.kk.cachesync.controller;

import lombok.RequiredArgsConstructor;
import org.kk.cachesync.dto.MemberDto;
import org.kk.cachesync.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/:id")
    public MemberDto findById(@RequestParam Long id) {
        return memberService.findMemberById(id);
    }
}
