package com.bs.spring.memo.model.service;

import com.bs.spring.memo.model.dto.Memo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemoService {
    List<Memo> searchMemoListAll();

    int addMemo(Memo memo);

    int deleteMemo(String id);
}
