package com.bs.spring.member.controller;

import com.bs.spring.member.model.dto.Member;
import com.bs.spring.member.model.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

//@AllArgsConstructor
//final로 선언된 필드를 매개변수로 선언하는 생성자
//@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"})
@Slf4j//필드에 log변수를 추가
public class MemberController {

    //private final Logger logger= LoggerFactory.getLogger(MemberController.class);


    private final MemberService service;
    private final BCryptPasswordEncoder passwordEncoder;
    //private DemoController controller=new DemoController();
    private BCryptPasswordEncoder encoder;

    @Autowired
    public MemberController(MemberService service, BCryptPasswordEncoder encoder, BCryptPasswordEncoder passwordEncoder) {
        this.service = service;
        this.encoder = encoder;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping("/login.do")
    public String login(String userId, String pw, Model model){
//                        HttpSession session) {
        Member member=service.selectMemberById(userId);
//        passwordEncoder.encode(pw);
        //원본값과 암호화값을 비교하는 메소드를 제공
        //passwordEncoder.matches(pw, member.getPassword());
//        if(member==null||!member.getPassword().equals(pw)){
        if(member ==null ||!passwordEncoder.matches(pw, member.getPassword())){
            //로그인 실패
            model.addAttribute("msg",
                    "아이디와 패스워드가 일치하지 않습니다.");
            model.addAttribute("loc","/");
            return "common/msg";
        }else{
            //로그인 성공
//            session.setAttribute("loginMember",member);
            model.addAttribute("loginMember",member);
            return "redirect:/";
        }
    }

    @RequestMapping("/logout.do")
    public String logout(SessionStatus status){
        if(!status.isComplete()){
            status.setComplete();//session삭제하는 메소드
        }
        return "redirect:/";
    }
    // /member/enrollmember.do -> /WEB-INF/views/member/enrollmember.jsp
//    @GetMapping("/enrollmember.do")
//    public String enrollMemberPage(){
//        return "member/enrollMember";
//    }
    @GetMapping("/enrollmember.do")
    public void enrollmember(@ModelAttribute("member") Member m){
        //logger.debug("회원가입화면 이동");
        log.debug("회원가입화면 이동");
        int age=190;
        //logger.debug("회원 : {} 나이 : {}",m,age);
        log.debug("회원 : {} 나이 : {}",m,age);
    }

    @PostMapping("/enrollmemberend.do")
    public String enrollMemberEnd(Member inputMember,Model model){
        //BCryptPasswordEncoder를 이용해서 비밀번호 암호화하기
        String encPw=encoder.encode(inputMember.getPassword());
        System.out.println(encPw);
        inputMember.setPassword(encPw);
        int result=service.saveMember(inputMember);
        String msg,loc,viewName="common/msg";
        if(result>0){
//            msg="회원가입성공";
//            loc="/";
            viewName="redirect:/";
        }else{
            msg="회원가입실패";
            loc="/member/enrollmember.do";
            model.addAttribute("msg",msg);
            model.addAttribute("loc",loc);
        }
        return viewName;
    }



}
