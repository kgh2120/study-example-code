package org.kk.cachesync.dto;

import lombok.Data;
import org.kk.cachesync.entity.Member;

@Data
public class MemberDto {
    private String name;
    private int age;
    private String statusMessage;

    public MemberDto(Member member) {
        this.name = member.getName();
        this.age = member.getAge();
        this.statusMessage = member.getStatusMessage();
    }
}
