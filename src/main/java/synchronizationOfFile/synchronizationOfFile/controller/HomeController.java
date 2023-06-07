package synchronizationOfFile.synchronizationOfFile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import synchronizationOfFile.synchronizationOfFile.domain.FileInfo;
import synchronizationOfFile.synchronizationOfFile.repository.ConnectListRepository;
import synchronizationOfFile.synchronizationOfFile.repository.FileRepository;
import synchronizationOfFile.synchronizationOfFile.repository.MemberRepository;
import synchronizationOfFile.synchronizationOfFile.repository.SharedFileListRepository;

@Controller
public class HomeController {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ConnectListRepository connectListRepository;
    @Autowired
    FileRepository fileRepository;
    @Autowired
    SharedFileListRepository sharedFileListRepository;

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
        model.addAttribute("sharedFiles", sharedFileListRepository.findBySharedMemberId(memberId));

        model.addAttribute("memberId", memberId);
        return "main";
    }

    @GetMapping("/updateFile")
    public String updateFilePage(Model model, @RequestParam("updateFile") String updateFile, @RequestParam("updateMemberId") Long id) {
        FileInfo tmp = fileRepository.findByName(updateFile);

        model.addAttribute("updateFile", tmp.getName()); // 파일 이름
        model.addAttribute("updatedAt", tmp.getUpdatedAt()); // 파일 업데이트 시각
        model.addAttribute("memberId", id); // 업데이트를 진행하려는 사용자의 id

        return "updateFile";
    }

}
