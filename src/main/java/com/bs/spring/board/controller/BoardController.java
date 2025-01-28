package com.bs.spring.board.controller;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {


    private final BoardService boardService;

    @RequestMapping("/boardList.do")
    public void boardList(Model model
            , @RequestParam(defaultValue="1") int cPage
            , @RequestParam(defaultValue="10") int perPage
            , HttpSession request){

            List<Board> boardList = boardService.findBoard(Map.of("cPage", cPage,"perPage",perPage));

    }

}
