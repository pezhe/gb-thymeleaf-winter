package ru.gb.gbthymeleafwinter.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.gbapi.security.AuthenticationUserDto;
import ru.gb.gbthymeleafwinter.clients.AuthGateway;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {

    private final AuthGateway authGateway;

    @GetMapping
    public String loginForm(Model model) {
        model.addAttribute("auth", AuthenticationUserDto.builder().build());
        return "login";
    }

    @PostMapping
    public String processLogin(AuthenticationUserDto user, HttpServletResponse response) {
        response.addCookie(new Cookie("jwt", authGateway.login(user).getBody().get("token")));
        return "redirect:/product/all";
    }

}
