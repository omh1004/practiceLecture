package com.bs.spring.member.model.dao;

import com.bs.spring.member.model.dto.Member;
import org.apache.ibatis.session.SqlSession;


public interface MemberDao {
    Member findMemberById(SqlSession session, String id);

    int saveMember(SqlSession session, Member member);
}
