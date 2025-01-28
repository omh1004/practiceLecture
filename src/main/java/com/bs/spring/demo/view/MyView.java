package com.bs.spring.demo.view;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@Component
public class MyView implements View {


    //View를 커스터마이징 해서 응답값을 보내줄수 있다.
    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        System.out.println(model);
        response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("내가 만든 응답이댜");
    }
}
