package com.bs.spring.demo.controller;

import com.bs.spring.demo.model.dto.Address;
import com.bs.spring.demo.model.dto.Demo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

@Controller
public class DemoController {

    @RequestMapping("/demo/demo.do")
    public String demo(@ModelAttribute("demo") Demo demo){
        return "demo/demo";
    }
    // 매핑메소드 파라미터 처리하기 -> 매개변수 활용하기
    // HttpServletRequest, HttpServletResponse활용하기
    @RequestMapping("/demo/demo1.do")
    public void demo1(HttpServletRequest request,
                      HttpServletResponse response,
                      HttpSession session)
            throws IOException, ServletException {
        System.out.println(request);
        System.out.println(response);
        String name=request.getParameter("devName");
        int age=Integer.parseInt(request.getParameter("devAge"));
        String gender=request.getParameter("devGender");
        String[] devLang=request.getParameterValues("devLang");
        System.out.println(name);
        System.out.println(age);
        System.out.println(gender);
        System.out.println(Arrays.toString(devLang));
        Demo d=Demo.builder().devName(name)
                .devAge(age).devGender(gender)
                .devLang(devLang).build();

        request.setAttribute("demo",d);

        //HttpSession이용하기
        session.setAttribute("loginMember","admin");


        request.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp")
                .forward(request,response);



//        response.setContentType("text/plain;charset=utf-8");
//        PrintWriter out= response.getWriter();
//        out.print(name);
//        out.print(age);
//        out.print(gender);
//        out.print(Arrays.toString(devLang));
//        return "";
    }


    // 파라미터값을 매개변수와 매칭하여 받아오기
    // 매개변수 선언할때 파라매터의 key 값과 동일하게 설정
    //devName, devAge, devEmail, devGender, devLang 파라미터를 전송
    //request.getParameter() 메소드를 직접 사용하지 않아도 됨!
    @RequestMapping("/demo/demo2.do")
    public String demo2(String devName, int devAge, String devEmail , String devGender
                        , String[] devLang
                        , Model model) {

        //RequestParam
        //요청과 응답을 같이 한다.
        //Model은 요청과 응답을 같이한다.
        //파라미터 데이터가 필수값일때만 활용을 해야한다.
        System.out.println(devName);
        System.out.println(devAge);
        System.out.println(devEmail);
        System.out.println(devGender);
        System.out.println(Arrays.toString(devLang));
        Demo demo = Demo.builder().devName(devName).devAge(devAge).devGender(devGender).devLang(devLang).build();

        //model에 저장하기
        // addAttribute()메소드를 이용해서 데이터를 저장할 수 있다.
        model.addAttribute("demo",demo);

        return "demo/demoResult";
    }


    //파라미터와 연결되는 매개변수 추가 설정하기
    //명칭이 다를떄 연결, 기본값 설정, 필수값 설정
    //@RequestParam 어노테이션을 이용
    @RequestMapping("/demo/demo3.do")
    public String demo3(
            @RequestParam(value="devName") String name
            ,@RequestParam(value="devAge", defaultValue="10") int age
            , @RequestParam(value="devEmail") String email
            , @RequestParam(required = false) String devGender
            , @RequestParam(required = false) String[] devLang
            , Model model) {

        System.out.println(name);
        System.out.println(age);
        System.out.println(devGender);
        System.out.println(Arrays.toString(devLang));
        System.out.println(email);

        return "demo/demoResult";
    }

    //파라미터를 DTO로 바로 저장하기
    //파라미터에 key와 dto의 필드명이 일치하는 값만 저장
    //DTO에 필드에 다른 클래스가 있으면, 저장할 수 없다.
    //날짜설정 java.util.Date는 파싱해주지 못함
    //@ModelAttribute 어노테이션 -> Model에 바로저장해줌
    @RequestMapping("/demo/demo4.do")
    public String demo4(@ModelAttribute("d") Demo demo, Address address) {
        demo.setAddress(address);
        System.out.println(demo);


        return "demo/demoResult";
    }

    //파라미터를 Map으로 저장하기
    @RequestMapping("/demo/demo5.do")
    public String demo5(@RequestParam Map param ,String[] devLang, Model model) {

        param.put("devLang",devLang);
        System.out.println(param);
        model.addAttribute("demo",param);

        return "demo/demoResult";
    }

    //추가정보 가져오기
    //Session저장된 값, Cookie값, Header값 가져오기
    //@SessionAttribute(value="key") ->session
    //@CookieValue(value="key") -> Cookie에 저장된값
    //@RequestHeader(value="key") -> RequestHeader
    @RequestMapping("/demo/demo6.do")
    public String demo6(Demo demo
                        ,@SessionAttribute(value="sessionId",required = false) String id
                        ,@CookieValue(value="lunch") String menu
                        ,@RequestHeader(value="Accept") String accept)  {

        System.out.println(id);
        System.out.println(menu);
        System.out.println(accept);
        return "demo/demoResult";

    }

    //매핑메소드 리턴
    // String -> ViewResolver가 처리하는 대로 화면을 출력
    // ModelAndView -> Model, view 정보를 한번에 저장하는 객체
    // 인터셉터나 AOP에서 값을 확인 하는 용도로도 쓰인다.
    @RequestMapping("/demo/demo7.do")
    public ModelAndView demo7(Demo demo,ModelAndView mv) {
        //데이터 저장하기
        mv.addObject("demo",demo);
        //view설정하기
        mv.setViewName("demo/demoResult");

        ModelAndView mv1 = new ModelAndView("demo/demoResult"
        ,"demo",demo);

        //view정보확인하기
        Map<String,Object> modelData = mv1.getModel();
        System.out.println(modelData);


        return mv1;
    }

    //리턴값으로 객체 설정하기 -> 데이터만 전송
    //@ResponseBody
    //ajax 요청처리, RestAPI로 서비스를 구성할 때 사용
    @RequestMapping("/demo/demo8.do")
    @ResponseBody
    public Demo returnObj() {
        return Demo.builder().devName("ddd").build();

    }

    @RequestMapping("/demo/demo9.do")
    @ResponseBody
    public Demo testObj(@RequestBody Demo demo) {
        System.out.println(demo);
        return demo;
    }

//    @Autowired
//    private MyView myView;
//
//    //View구현체로 응답하기
//    @RequestMapping("/demo/demo10.do")
//    public View myViewTest(MyView view,Model model) {
//        model.addAttribute("test","나의 view");
//        return view;
//    }
}
