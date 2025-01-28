package com.bs.spring.memo.controller;

import com.bs.spring.memo.model.dto.Memo;
import com.bs.spring.memo.model.service.MemoService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/memo")
public class MemoController {

    private MemoService memoService;
    private BCryptPasswordEncoder encoder;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
        this.encoder = new BCryptPasswordEncoder();
    }


    @RequestMapping("/memolist.do")
    public String memoListView(Model model){

        List<Memo> memoList = memoService.searchMemoListAll();

        model.addAttribute("memoList", memoList);

        return "memo/memoMain";
    }

    @RequestMapping("/addMemo.do")
    public String addMemo(Model model,Memo memo){


        memo.setPassword(encoder.encode(memo.getPassword()));

       int result =  memoService.addMemo(memo);
        String msg, loc,viewName="common/msg";
        if(result>0){
//            msg="회원가입성공!";
//            loc="/";
            viewName=  "redirect:/memo/memolist.do";
        }else{
            msg="댓글 등록 실패 ";
            //회원가입이 실패하면 다시 회원가입 화면으로 옮김

            loc="/memo/memolist.do";
            model.addAttribute("msg",msg);
            model.addAttribute("loc",loc);
        }

        return viewName;


    }

    @RequestMapping("/deleteMemo.do")
    public String deleteMemo(Model model,String id){


        int result = memoService.deleteMemo(id);

        String msg, loc,viewName="common/msg";
        if(result>0){
//            msg="회원가입성공!";
//            loc="/";
            //controller에 요청할때는 redirect를 사용해야 한다.
            viewName=  "redirect:/memo/memolist.do";
        }else{
            msg="댓글 삭제 실패 ";
            //회원가입이 실패하면 다시 회원가입 화면으로 옮김

            loc="/memo/memolist.do";
            model.addAttribute("msg",msg);
            model.addAttribute("loc",loc);
        }

        return viewName;
    }


}
