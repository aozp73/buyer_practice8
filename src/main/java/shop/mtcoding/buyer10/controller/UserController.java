package shop.mtcoding.buyer10.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer10.model.User;
import shop.mtcoding.buyer10.model.UserRepository;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpSession session;

    @GetMapping("/loginForm")
    public String loginForm(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = "";

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("remember")) {
                username = cookie.getValue();
            }
        }

        request.setAttribute("remember", username);
        return "user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @PostMapping("/login")
    public String login(String username, String password, String remember, HttpServletResponse response) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        if (remember == null) {
            remember = "";
        }

        if (user == null) {
            return "redirect:/loginForm";
        } else {
            if (remember.equals("on")) {
                Cookie cookie = new Cookie("remember", username);
                response.addCookie(cookie);
            } else {
                Cookie cookie = new Cookie("remember", username);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

            session.setAttribute("principal", user);
            return "redirect:/";
        }

    }

    @PostMapping("/join")
    public String join(String username, String password, String email) {
        int res = userRepository.insert(username, password, email);
        if (res != 1) {
            return "user/joinForm";
        } else {
            return "user/loginForm";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
