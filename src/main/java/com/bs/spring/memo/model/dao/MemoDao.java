package com.bs.spring.memo.model.dao;

import com.bs.spring.memo.model.dto.Memo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public interface MemoDao {
    List<Memo> searchMemoListAll(SqlSession session);
    int addMemo(SqlSession session, Memo memo);

    int deleteMemo(SqlSession session,String id);
}
