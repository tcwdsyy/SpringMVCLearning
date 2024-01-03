package com.syy.learn_springmvc.filter;

import com.syy.learn_springmvc.entity.User;
import com.syy.learn_springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class loginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //System.out.println("test");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestURL = req.getRequestURI();
//        if(requestURL.contains("login")){
//            filterChain.doFilter(req,resp);
//        }else{
//            HttpSession session = req.getSession();
//            User user = (User) session.getAttribute("user");
//            if(user==null){
//                req.getRequestDispatcher("/login.jsp").forward(req,resp);
//            }
//        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
