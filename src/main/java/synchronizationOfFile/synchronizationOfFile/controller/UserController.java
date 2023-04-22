package synchronizationOfFile.synchronizationOfFile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @PostMapping("/create")
    public String create() {


        return "home";
    }

    @PostMapping("/login")
    public String login(){
        return "home";
    }

}
