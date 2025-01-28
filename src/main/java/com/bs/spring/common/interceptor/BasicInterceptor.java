package com.bs.spring.common.interceptor;

import com.bs.spring.demo.model.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("basicinter")
public class BasicInterceptor implements HandlerInterceptor {

    @Autowired
    private DemoService demoService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {




        //request값을 미리 확인할 수 있다.
        System.out.println("인터셉터 실행!!! preHandle");
        System.out.println("요청주소 : "+request.getRequestURI());
        //HandlerMethod handlerMethod = (HandlerMethod) handler;
        //만들어진 bean반환
        //System.out.println(handlerMethod.getBean().getClass().getName());
        //System.out.println(handlerMethod.getMethod().getName());


//        request.setAttribute("msg", "못감");
//        request.setAttribute("loc","/");
//        request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("인터셉터 실행!!! postHandle");
        System.out.println(modelAndView);
        modelAndView.addObject("msg2","금요일 마지막시간 화이팅!");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("인터셉터 실행!!! afterCompletion");
        //응답한 페이지(jsp)에서 에러가 발생하면, 에러가 발생한다.
        System.out.println(ex);
        if(ex!=null){
            //response.sendRedirect(request.getContextPath());
        }
    }
}
