package com.bs.spring.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller // 클라이언트에 요청을 받아 처리하는 메소드를 가지고 있는 메소드
public class BasicController {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private final Logger log = LogManager.getLogger(BasicController.class);

    @RequestMapping("/")
    public String index(HttpSession session, HttpServletResponse response) {

        //session 저장
        session.setAttribute("sessionId", "bsyoo");

        //Cookie 저장
        Cookie c = new Cookie("lunch","갑을그래이트");
        c.setMaxAge(60*60*24);
        response.addCookie(c);
        //System.out.println("index 메소드실행 ");
        //logger를 이용해서 출력하기
        //레벨에 따라 다 출력할 수도, 하나만 출력할 수도 있다.
        //설정한 것보다 레벨이 낮은것은 모두 출력한다.
        log.debug("debug로 출력하기!");
        log.info("info로 출력하기 ");
        log.warn("Warn로 출력하기 ");
        log.error("error로 출력하기 ");

        return "index";//WEB-INF/views/controller반환값.jsp
    }

    @RequestMapping("/messagetest/{locale}")
    @ResponseBody
    public Map<String,String> messagetest(@PathVariable String locale, HttpSession session) {

        String usmsg=webApplicationContext.getMessage("info",null,Locale.US);
        String uskor=webApplicationContext.getMessage("info",null,Locale.KOREA);
        String jsmsg=webApplicationContext.getMessage("info",null,Locale.JAPAN);


        String myusMsg = webApplicationContext.getMessage("myinfo",new Object[]{"bsyoo",19},Locale.US);
        String mykrMsg = webApplicationContext.getMessage("myinfo",new Object[]{"bsyoo",19},Locale.KOREA);
        String myjaMsg = webApplicationContext.getMessage("myinfo",new Object[]{"bsyoo",19},Locale.JAPAN);

        Map<String,String> map=new HashMap<String,String>();
        map.put("usmsg",usmsg);
        map.put("uskor",uskor);
        map.put("jsmsg",jsmsg);
        map.put("myusMsg",myusMsg);
        map.put("mykrMsg",mykrMsg);
        map.put("myjaMsg",myjaMsg);
       return  map;
    }

    @RequestMapping("/errorhandler")
    public String errorhandler() {
        if(1==1){
            throw new RuntimeException("errorhandler");
        }
        return "redirect:/";
    }

    @RequestMapping("/myexception")
    public String myException() {
        throw new RuntimeException("나의 에러를 처리해봐!");
    }

}
