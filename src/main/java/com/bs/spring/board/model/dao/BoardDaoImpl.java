package com.bs.spring.board.model.dao;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {
    @Override
    public List<Board> findBoard(SqlSession session, Map<String, Integer> param) {
//        Integer cPage = param.get("cPage");
//        Integer numPerPage = param.get("numPerPage");
//
//        //원하는 범위 만큼만 가져오는 객체
//        RowBounds rowBounds
//                = new RowBounds((cPage-1) * numPerPage, numPerPage);

        //넘어온 Map에서 cPage 와 ,perPage를 파라메터로 받는다.
        Integer cPage = param.get("cPage");
        Integer numPerPage = param.get("numPerPage");

        //마이바티스에서 제공하는 RowBounds 객체를 이용하면,
        //



        return session.selectList("board.findBoard",null, rowBounds);
    }

    @Override
    public int insertBoard(SqlSession session, Board board) {
        return session.insert("board.insertBoard", board);
    }

    @Override
    public int boardCount(SqlSession session) {
        return session.selectOne("board.boardCount");
    }

    @Override
    public int insertAttachment(SqlSession session, Attachment attachment) {
        return session.insert("board.insertAttachment", attachment);
    }

    @Override
    public Board findBoardById(SqlSession session, int no) {
        return session.selectOne("board.findBoardByNo", no);
    }
}
