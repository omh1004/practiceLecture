package com.bs.spring.demo.controller;

import com.bs.spring.demo.model.dto.Demo;
import com.bs.spring.demo.model.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

//@AllArgsConstructor
@Controller
@RequestMapping("/demo")
public class DemoController2 {
       // @Autowired
        private DemoService demoService;

    @Autowired
    public DemoController2(DemoService demoService) {
        this.demoService = demoService;
    }

    //요청을 매핑하는 어노테이션
        //@RequestMapping(value="url",params="",headers="",consumes="",produces="",method="")
        //요청 메소드에 따른 매핑 어노테이션
        @RequestMapping(
                            value="/request/test"
                            ,method= RequestMethod.GET
                            ,params = {"accessKey","test=유병승","!data"}
                            ,consumes = "applicaton/json"
                        )
        public String test(){

            System.out.println("test메소드 실행");
            return "/";
        }

        //@GetMapping == @RequestMapping("", method=RequestMethod.GET)
        @GetMapping(value="/test2")
        public String test2(){
            System.out.println("test2실행");
            //요청에 대한 응답값을 보내줄떄는 Dispatcher로 넘어가지만
            //요청한 곳으로 다시 화면을 옮기고 싶을때는 redirect를 쓴다.
            return "redirect:/";
        }
        //@PostMapping
        @PostMapping(value="/test3")
        public String test3(){
            System.out.println("test3실행");
            return "redirect:/";
        }

        //@PutMapping
        //@PatchMapping
        //@DeleteMapping


    @RequestMapping("/insertdemo.do")
    public String insertDemo(@Validated Demo demo, BindingResult bindingResult ,Model model){

        //요청이 넘어온 값을 Validated 로 유효성 검사 하고, 그 결과 값을 BindingResult로 결과를 return 준다.

        if(bindingResult.hasErrors()){
            System.out.println("에러발생!");
            System.out.println(bindingResult);
            bindingResult.getAllErrors().forEach(System.out::println);

            return "demo/demo";
        }








        //DB mybatis
        //1. mybatis.jar
        //2. mybatis 설정파일 mybatis-config, mapper.xml
        //3. SqlSession
        int result = demoService.insertDemo(demo);
        System.out.println("result:: "+result);
        if(result >0){
            model.addAttribute("msg", "저장성공");
            model.addAttribute("loc", "/");
        }else{
            model.addAttribute("msg","저장실패");
            model.addAttribute("loc", "/demo/demo.do");
        }

        return "common/msg";
    }


    @RequestMapping(value="/demoSelectAll.do")
    public ModelAndView demoSelectAll(ModelAndView model){
        List<Demo> demoList = demoService.selectDemoList();
        System.out.println("demoList"+demoList);

        model.addObject("demoList",demoList);
        //view설정하기
        model.setViewName("demo/demoList");



        return model;
    }

    @GetMapping("/demolist.do")
    public String demoList(Model model){
        List<Demo> demos = demoService.selectDemoList();
        model.addAttribute("demos",demos);
        return "demo/demoList";
    }
}
