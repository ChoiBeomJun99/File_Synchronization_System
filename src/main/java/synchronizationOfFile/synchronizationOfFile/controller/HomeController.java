package synchronizationOfFile.synchronizationOfFile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import synchronizationOfFile.synchronizationOfFile.repository.ConnectListRepository;
import synchronizationOfFile.synchronizationOfFile.repository.FileRepository;
import synchronizationOfFile.synchronizationOfFile.repository.MemberRepository;

@Controller
public class HomeController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ConnectListRepository connectListRepository;
    @Autowired
    FileRepository fileRepository;

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

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/main")
    public String mainPage(Model model, @RequestParam Long memberId) {
        model.addAttribute("files", fileRepository.findAll());
        model.addAttribute("member", memberRepository.findAll());
        model.addAttribute("connect_list", connectListRepository.findAll());

        System.out.println("memberId : " + memberId);
        model.addAttribute("memberId", memberId);
        return "main";
    }
}
