//package com.bs.spring.board.controller;
//
//import com.bs.spring.board.model.dto.Attachment;
//import com.bs.spring.board.model.dto.Board;
//import com.bs.spring.board.model.service.BoardService;
//import com.bs.spring.common.PageFactory;
//import com.bs.spring.common.error.MyException;
//import com.bs.spring.common.excelconvert.ExcelForBoard;
//import com.bs.spring.common.myannotation.MyAnnotation;
//import com.bs.spring.member.model.dto.Member;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.servlet.View;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.*;
//import java.net.URLEncoder;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//@RequiredArgsConstructor //final 필드만 가지고 생성자
//@Controller
//@RequestMapping("/board")
//@Slf4j
//
//public class ViewController {
//
//
//    private final BoardService boardService;
//
////    public ViewController(BoardService boardService) {
////        this.boardService = boardService;
////    }
//
//    @MyAnnotation
//    @GetMapping("/boardList.do")
//    public void viewBoard(@RequestParam(defaultValue = "1") int cPage
//            , @RequestParam(defaultValue = "5") int numPerPage
//            , Model model, HttpServletRequest request) {
//        HttpSession session = request.getSession();
//
//        //if(1==1) throw new MyException("일부러발생시킨 에러");
//
////        if(cPage == null){
////            cPage=1;
////        }
////
////        if(numPerPage==null){
////            numPerPage=10;
////        }
//
//        List<Board> boardList =
//                boardService.findBoard(
//                        Map.of("cPage", cPage, "numPerPage", numPerPage));
//
//        int boardCount = boardService.boardCount();
//
//        Member member = (Member) session.getAttribute("loginMember");
//
//        String pageBar = PageFactory.pageBar(cPage, numPerPage, boardCount, "boardList.do");
//
////        //pageBar생성하기
////        int totalData=boardCount;
////        int totalPage=(int)Math.ceil((double)totalData/numPerPage);
////        int pageBarSize=5;//페이바에 출력될 숫자의 갯수
////        int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
////        int pageEnd=pageNo+pageBarSize-1;
////
////
////        String pageBar="<ul class='pagination justify-content-center'>";
////
////        if(pageNo==1) {
////            pageBar+="<li class='page-item disabled'>";
////            pageBar+="<a class='page-link' href='#'>이전</a>";
////            pageBar+="</li>";
////        }else {
////            pageBar+="<li class='page-item'>";
////            pageBar+="<a class='page-link' href='"+
////                    request.getRequestURI()
////                    +"?cPage="+(pageNo-1)
////                    +"&numPerPage="+numPerPage
////                    +"'>이전</a>";
////            pageBar+="</li>";
////        }
////        while(!(pageNo>pageEnd||pageNo>totalPage)) {
////            if(pageNo==cPage) {
////                pageBar+="<li class='page-item disabled'>";
////                pageBar+="<a class='page-link' href='#'>"+pageNo+"</a>";
////                pageBar+="</li>";
////            }else {
////                pageBar+="<li class='page-item'>";
////                pageBar+="<a class='page-link' href='"+
////                        request.getRequestURI()
////                        +"?cPage="+(pageNo)
////                        +"&numPerPage="+numPerPage
////                        +"'>"+pageNo+"</a>";
////                pageBar+="</li>";
////            }
////            pageNo++;
////        }
////
////        if(pageNo>totalPage) {
////            pageBar+="<li class='page-item disabled'>";
////            pageBar+="<a class='page-link' href='#'>다음</a>";
////            pageBar+="</li>";
////        }else {
////            pageBar+="<li class='page-item'>";
////            pageBar+="<a class='page-link' href='"+
////                    request.getRequestURI()
////                    +"?cPage="+(pageNo)
////                    +"&numPerPage="+numPerPage
////                    +"'>다음</a>";
////            pageBar+="</li>";
////        }
////        pageBar+="</ul>";
//
//
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("totalContents", boardCount);
//        model.addAttribute("pageBar", pageBar);
//        // model.addAttribute("pageBar", pageBar);
//
//    }
//
//    @RequestMapping("/addBoard.do")
//    public String insertBoard(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        Member member = (Member) session.getAttribute("loginMember");
//        return "board/boardWrite";
//    }
//
////    @RequestMapping("/boardWrite.do")
////    public String boardWrite(Board board, Model model) {
////
////
////       // int result = boardService.insertBoard(session, board);
////
////        String msg, loc, viewName = "common/msg";
////        if (result > 0) {
//////            msg="회원가입성공!";
//////            loc="/";
////            viewName = "redirect:/";
////        } else {
////            msg = "글등록 실패 ";
////            //회원가입이 실패하면 다시 회원가입 화면으로 옮김
////
////            loc = "/board/addBoard.do";
////            model.addAttribute("msg", msg);
////            model.addAttribute("loc", loc);
////        }
////
////        return viewName;
////
////
////    }
//
//
//    @PostMapping(value = "/writeboard.do")
//    @Transactional(propagation = Propagation.MANDATORY)
//    public String writeBoard(Board board, MultipartFile[] upFile
//            , HttpSession session) {
////        log.debug("파일명 : "+upFile.getOriginalFilename());
////        log.debug("파일크기 : "+upFile.getSize());
////        log.debug("{}",board);
//
//        //파일저장하기
//        //1.파일을 저장할 절대 경로가 필요 ->
//        String path = session.getServletContext().getRealPath("/resources/upload/board/");
//        File dir = new File(path);
//        if (!dir.exists()) {
//            dir.mkdirs();
//        }
//        List<Attachment> files = new ArrayList<>();
//        if (upFile != null) {
//            for (MultipartFile file : upFile) {
//                //2.리네임규칙을 생성
//                String oriname = file.getOriginalFilename();
//                // 2-1 원본파일 확장자 가져오기
//                String ext = oriname.substring(oriname.lastIndexOf("."));
//                // 2-2 파일명이 중복되지 않게 랜덤값 가져오기
//                int rnd = (int) (Math.random() * 1000) + 1;
//                // 2-3 파일명이 중복되지 않게 업로드 날짜를 밀리세컨초까지 가져오기
//                Date d = new Date(System.currentTimeMillis());
//                // 2-4 날짜를 문자열로 패턴에 맞춰서 변경해주기
//                SimpleDateFormat sdf =
//                        new SimpleDateFormat("yyyyMMddHHmmssSSS");
//                // 2-5 위 생성한 데이터들을 합쳐서 파일명 생성하기
//                String rename = "BSLOVE" + sdf.format(d) + "_" + rnd + ext;
//                //3. 생성한 파일명으로 파일 저장하기
//                //   MultipartFile클래스가 제공하는 tranferTo메소드를 이용해서 저장
//                try {
//                    file.transferTo(new File(path, rename));
//
//                    files.add(Attachment
//                            .builder()
//                            .originalFileName(oriname)
//                            .renamedFileName(rename)
//                            .build());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    log.error(file.getOriginalFilename() + "파일 저장 실퍠 ");
//                    if (files.size() > 0) {
//                        for (Attachment attachment : files) {
//                            File delFile = new File(path, attachment.getRenamedFileName());
//                            if (delFile.exists()) {
//                                delFile.delete();
//                            }
//                        }
//                    }
//                }
//            }
//            //실패처리
//        }
//        String viewName;
//        try {
//            //db에 저장하기
//            int result = boardService.insertBoard(board, files);
//            viewName="redirect:/board/boardList.do";
//        }catch(MyException e){
//            //게시글 저장 실패
//            return "redirect:/board/boardform.do";
//
//
//        }
//
//        return viewName;
//
//    }
//
//
//    @GetMapping("/boardview.do")
//    public String boardView(int no, Model model) {
//        Board b= boardService.findBoardById(no);
//        model.addAttribute("board",b);
//        return "board/boardview";
//    }
//
//    @MyAnnotation
//    @GetMapping("/filedown.do")
//    public void fileDown(
//                             String oriname
//                            ,String rename
//                            ,HttpServletResponse response
//                            ,OutputStream out
//                            ,HttpSession session
//    ,@RequestHeader(value="user-agent") String header)   throws IOException {
//
//        String path = session.getServletContext().getRealPath("/resources/upload/board/");
//        File downLoadFile = new File(path, rename);
//        if(!downLoadFile.exists()) {
//            //파일없음!
//            //에러페이지 전송
//            throw new MyException("파일없음");
//
//        }else{
//            try(FileInputStream fis = new FileInputStream(downLoadFile);
//                BufferedInputStream bis = new BufferedInputStream(fis);
//                BufferedOutputStream bos = new BufferedOutputStream(out)) {
//
//                //원본파일명으로 다운로드하기
//                //원본파일명이 한글일때, 한글이 꺠짐 - 바이트 단위로 넘어가기 때문
//                //한글이 깨지지 않게 인코딩 처리
//                //윈도우에서 제공하는 인코딩 처리를 위함
//                boolean isMS = header.contains("Trident")||header.contains("MSIE");
//                String encodeFilename="";
//                if(isMS){
//                    encodeFilename= URLEncoder.encode(oriname,"UTF-8");
//                    //띄어쓰기에 대해서 +로 표현하기 때문에 , replace함
//                    encodeFilename=encodeFilename.replace("\\+","%20");
//                }else{
//                    encodeFilename= new String(oriname.getBytes("UTF-8"),"ISO-8859-1");
//
//                }
//                //특정한 파일 지정이 아니라 파일이라는 것을 명시 하는 encoding
//                response.setContentType("application/octet-stream;charset=utf-8");
//                response.setHeader("Content-Disposition", "attachment;filename="+encodeFilename);
//
//                int data=1;
//                while(bis.read()!=-1){
//                    bos.write(data);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    //엑셀다운로드 시키기
//    @GetMapping("/exceldownload")
//    public View excelDownload(Model model){
//        List<Board> boards =
//                boardService.findBoard(Map.of("cPage",1,"numPerPage",20));
//        model.addAttribute("boards", boards);
//
//
//
//
//        return new ExcelForBoard();
//
//    }
//}