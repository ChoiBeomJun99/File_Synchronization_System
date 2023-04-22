package synchronizationOfFile.synchronizationOfFile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 8080으로 들어오면 이게 호출 됨
    public String home() {
        return "home";
    }

    @GetMapping("/create")
    public String createPage() {
        return "user/createForm";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "user/loginForm";
    }
}
