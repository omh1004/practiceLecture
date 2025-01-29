package com.bs.spring.board.controller;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.common.PageFactory;
import com.bs.spring.common.error.MyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            , @RequestParam(defaultValue="10") int numPerPage
            , HttpSession request){

            List<Board> boardList = boardService.findBoard(Map.of("cPage", cPage,"numPerPage",numPerPage));

            int boardCount = boardService.boardCount();

            String pageBar = PageFactory.pageBar(cPage,numPerPage,boardCount, "boardList.do");

            model.addAttribute("boardList", boardList);
            model.addAttribute("pageBar", pageBar);
            model.addAttribute("totalContents", boardCount);
    }

    @RequestMapping("/boardwrite.do")
    public String boardwrite(Board board, MultipartFile upFile,HttpSession request) {

        log.debug("boardwrite.do");
        log.debug("{}", board);
        //파일저장하기
        //1.파일을 저장할 절대 경로가 필요 ->
        //session이 context보다 가벼우므로, sesson에서 파일을 저장할 절대 경로를 가져온다.

        String path = request.getServletContext().getRealPath("/resources/upload/board");

        File uploadFile = new File(path);

        // 2.해당경로의 폴더가 없다면, 해당 폴더를 만들어야한다.
        if (!uploadFile.exists()) {
            uploadFile.mkdirs();
        }

        // 3. 넘어온 파일의 정보를 가져와서 Rename규칙을 생성한다.

        Attachment attachment = null;

        // 파일명 가져오기
        if (upFile != null) {

            String oriName = upFile.getOriginalFilename();

            //1. 원본확장자 가져오기
            String ext = oriName.substring(oriName.lastIndexOf("."));
            //2. 랜덤값 생성
            int rnd = (int) (Math.random() * 1000);
            //3. 날짜값 가져오기
            Date d = new Date(System.currentTimeMillis());
            //4. 날짜를 문자열로 패턴맞춰 변경해주기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

            String rename = "OMH" + sdf.format(d) + "_" + rnd + ext;

            // 값 저장에 성공했으면 실제로 파일을 해당 경로에 넣어 주어야한다.


            // 파일을 저장해주고, 파일저장에 성공하면, 객체에 값을 넣어주고, 그값을 db에 저장한다.
            try {

                upFile.transferTo(new File(path, rename));

                attachment = Attachment
                        .builder()
                        .originalFileName(oriName)
                        .renamedFileName(rename)
                        .build();

            } catch (IOException e) {
                log.error("파일저장실패");
            }
        }
            String viewName = "";
            try {
                int result = boardService.attachedFile(board, attachment);
                return "redirect:/board/boardList.do";
            } catch (MyException e) {
                return "redirect:/board/boardForm.do";
            }
        }


    }



