package com.bs.spring.memo.model.service;

import com.bs.spring.memo.model.dao.MemoDao;
import com.bs.spring.memo.model.dto.Memo;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoServiceImpl implements MemoService {



    private MemoDao memoDao;
    private SqlSession session;

    public MemoServiceImpl(MemoDao memoDao, SqlSession session) {
        this.memoDao = memoDao;
        this.session = session;
    }

    @Override
    public List<Memo> searchMemoListAll() {
        return memoDao.searchMemoListAll(session);
    }

    @Override
    public int addMemo(Memo memo) {
        return memoDao.addMemo(session,memo);
    }

    @Override
    public int deleteMemo(String id) {
        return memoDao.deleteMemo(session,id);
    }
}
