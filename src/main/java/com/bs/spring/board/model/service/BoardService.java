package com.bs.spring.board.model.service;


import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    List<Board> findBoard(Map<String, Integer> param);
    int insertBoard(Board board, List<Attachment> files);
    int boardCount();
    Board findBoardById(int no);
    int attachedFile(Board board, Attachment attachment);
}
