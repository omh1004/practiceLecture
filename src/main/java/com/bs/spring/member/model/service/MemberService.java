package com.bs.spring.member.model.service;

import com.bs.spring.member.model.dto.Member;


public interface MemberService {
    public Member selectMemberById(String id);
    int saveMember(Member member);
}
