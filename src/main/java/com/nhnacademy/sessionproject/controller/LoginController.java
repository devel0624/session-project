package com.nhnacademy.sessionproject.controller;

import com.nhnacademy.sessionproject.dto.LoginRequest;
import com.nhnacademy.sessionproject.service.LoginService;
import com.nhnacademy.sessionproject.session.Session;
import java.util.Optional;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private static final String USER_NAME = "USER_NAME";
    private static final String SESSION_ID = "SESSION_ID";
    private final Session session;

    private final LoginService loginService;

    public LoginController(Session session, LoginService loginService) {
        this.session = session;
        this.loginService = loginService;
    }

    @GetMapping
    public String index(@CookieValue(name = SESSION_ID, required = false) Optional<Cookie> cookie, Model model){

        if (cookie.isPresent()){
            String member = (String) session.getAttribute(USER_NAME);
            model.addAttribute("member", member);
        }

        return "index";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginRequest loginRequest, HttpServletResponse response){

        if (loginService.login(loginRequest)){
            String sessionId = session.getSessionId();

            session.setAttribute(USER_NAME,loginRequest.getId());

            Cookie cookie = new Cookie(SESSION_ID, sessionId);
            cookie.setHttpOnly(true);

            response.addCookie(cookie);
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(@CookieValue(name = SESSION_ID, required = false) Optional<Cookie> cookie,
                         HttpServletResponse response){

        if (cookie.isPresent()){
            session.removeAttribute(USER_NAME);

            cookie.get().setMaxAge(0);
            cookie.get().setValue(null);

            response.addCookie(cookie.get());
        }

        return "redirect:/";
    }
}
