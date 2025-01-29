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

        //넘어온 Map에서 cPage 와 ,perPage를 파라메터로 받는다.
        Integer cPage = param.get("cPage");
        Integer numPerPage = param.get("numPerPage");

        //마이바티스에서 제공하는 RowBounds 객체를 이용하면,
        //필요한 만큼의 데이터를 가져올 수있음

        RowBounds rowBounds = new RowBounds((cPage-1)*numPerPage, numPerPage);


        //파라메터가 없으므로 null 그리고, rowBounds 객체를 넣어준다.
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
