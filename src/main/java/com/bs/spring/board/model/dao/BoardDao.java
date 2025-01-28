package com.bs.spring.board.model.dao;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;


public interface BoardDao {
    List<Board> findBoard(SqlSession session, Map<String, Integer> param);
    int insertBoard(SqlSession session, Board board);
    int boardCount(SqlSession session);
    int insertAttachment(SqlSession session, Attachment attachment);
    Board findBoardById(SqlSession session, int no);

    // List<Attachment> findAttachByNo(SqlSession session, int no);
}
