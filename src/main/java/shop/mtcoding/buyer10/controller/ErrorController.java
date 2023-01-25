package shop.mtcoding.buyer10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/notfound")
    public String notfound() {
        return "error/notfound";
    }
}
