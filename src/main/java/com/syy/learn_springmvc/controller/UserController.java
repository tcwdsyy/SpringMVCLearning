package com.syy.learn_springmvc.controller;

import com.syy.learn_springmvc.entity.User;
import com.syy.learn_springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String index() {
        System.out.println("测试");
        return "index";
    }

    @RequestMapping("/loginForm")
    public String loginForm(){
        return "login";
    }

    @RequestMapping("/home")
    public ModelAndView show(
            HttpSession session){

        System.out.println("主页");
        User user = (User) session.getAttribute("user");
        System.out.println(user);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("home");
        mav.addObject("id",user.getId());
        mav.addObject("username",user.getUsername());
        return mav;
    }

    @RequestMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpSession session){

        System.out.println("logging in ....");
        ServletContext sc = session.getServletContext();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User loginUser = null;
        try {
            loginUser = userService.login(user); // 传入对象 通过MyBatis自动提取
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(loginUser);
        if (loginUser == null) {
            System.out.println("Fail");
            return "redirect:loginForm";
        } else {
            System.out.println("Success");
            String sessionId = session.getId();
            Map<String, String> map = (Map<String, String>) sc.getAttribute("loginMap");
            if (map == null) {
                map = new HashMap<>();
            }
            map.put(loginUser.getUsername(), sessionId);

            sc.setAttribute("loginMap", map);
            session.setAttribute("user", loginUser);
            return "redirect:home";
        }
    }
}
