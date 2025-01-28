package com.bs.spring.board.model.service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.common.error.MyException;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor

public class BoardServiceImpl implements BoardService {

  private final BoardDao boardDao;
  private final SqlSession session;


//  public BoardServiceImpl(BoardDao boardDao,SqlSession session) {
//    this.boardDao = boardDao;
//    this.session = session;
//  }

  @Override
  public List<Board> findBoard(Map<String, Integer> param) {
    return boardDao.findBoard(session, param);
  }

  @Override
  public int insertBoard(Board board, List<Attachment> files) {
      try {
        int result = boardDao.insertBoard(session, board);
        if(files.size() > 0) {
          files.stream().forEach(file->{
            file.setBoardNo(board.getBoardNo());
            boardDao.insertAttachment(session, file);
          });
        }
      }catch(RuntimeException e){
        e.printStackTrace();
        throw new MyException("게시글 저장실패");
      }



    return boardDao.insertBoard(session, board);
  }

  @Override
  public int boardCount() {
    return boardDao.boardCount(session);
  }

  @Override
  public Board findBoardById(int no) {
//    Board board=boardDao.findBoardById(session, no);
    //List<Attachment> files= boardDao.findAttachByNo(session,no);
    //board.setFiles(files);
    return boardDao.findBoardById(session,no);
  }
}
