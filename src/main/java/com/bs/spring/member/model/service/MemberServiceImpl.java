package com.bs.spring.member.model.service;

import com.bs.spring.member.model.dao.MemberDao;
import com.bs.spring.member.model.dto.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;


@Service
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao;
    private SqlSession session;

    public MemberServiceImpl(MemberDao memberDao, SqlSession session) {
        this.memberDao = memberDao;
        this.session = session;
    }


    @Override
    public Member selectMemberById(String id) {
        return memberDao.findMemberById(session,id);
    }

    @Override
    public int saveMember(Member member) {
        return memberDao.saveMember(session,member);
    }
}
