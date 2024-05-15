package com.syy.learn_springmvc.interceptor;

import com.syy.learn_springmvc.entity.User;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Order(1)
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        ServletContext sc = session.getServletContext();

        String sessionId = session.getId();
        User user = (User) session.getAttribute("user");
        Map<String, String> map = (Map<String, String>) sc.getAttribute("loginMap");

        System.out.println("拦截器:");
        if (user != null && map != null) {
            if (map.get(user.getUsername()).equals(sessionId)) {
                System.out.println("已登录");
                return true;
            } else {
                System.out.println("不好意思您已在其他地方登录还请您登录");
                response.sendRedirect("loginForm");
            }
        } else {
            System.out.println("不好意思您还没有登录！还请您登录");
            response.sendRedirect("loginForm");
        }
        return false;
    }
}
