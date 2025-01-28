package com.bs.spring.memo.model.dao;

import com.bs.spring.memo.model.dto.Memo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class MemoDaoImpl implements MemoDao {

    @Override
    public List<Memo> searchMemoListAll(SqlSession session) {
        return session.selectList("memo.searchMemoListAll");
    }

    @Override
    public int addMemo(SqlSession session, Memo memo) {
        return session.insert("memo.addMemo", memo);
    }

    @Override
    public int deleteMemo(SqlSession session, String id) {
        return session.delete("memo.deleteMemo", id);
    }
}
