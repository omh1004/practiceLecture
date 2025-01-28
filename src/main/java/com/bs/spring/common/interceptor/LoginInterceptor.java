package com.bs.spring.common.interceptor;

import com.bs.spring.member.model.dto.Member;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component("loginCheck")
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //로그인 체크하기
       HttpSession session = request.getSession();
       Member member = (Member) session.getAttribute("loginMember");

       if (member == null) {
            request.setAttribute("msg","로그인 후 이용할 수 있습니다.");
            request.setAttribute("loc","/");
            request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
            return false;
       }
       return true;
    }
}
