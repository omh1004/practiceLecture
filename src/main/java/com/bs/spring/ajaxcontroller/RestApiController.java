package com.bs.spring.ajaxcontroller;

import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins="http://localhost:52330")
public class RestApiController {
    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/boards")
    public List<Board> getMembers(@RequestParam(defaultValue="1")int cPage,
                                  @RequestParam(defaultValue = "10") int numPerPage) {

                List<Board> boards = boardService.findBoard(
                        Map.of("cPage",cPage,"numPerPage",numPerPage));

                    return boards;
    }
    @GetMapping("/boards/{boardNo}")
    public Board getBoard(@PathVariable int boardNo) {
        Board board = boardService.findBoardById(boardNo);
        return board;
    }

    //@ResponseEntity클래스를 이용해서 응답처리할 수 있음
    @PostMapping("/member")
    public Member createMember(@RequestBody Member member) {
        log.debug("member: {}", member);
        int result = memberService.saveMember(member);
        return result > 0 ? member : null;
    }





}
