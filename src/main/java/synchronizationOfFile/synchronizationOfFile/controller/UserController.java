package synchronizationOfFile.synchronizationOfFile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import synchronizationOfFile.synchronizationOfFile.domain.Member;
import synchronizationOfFile.synchronizationOfFile.repository.ConnectListRepository;
import synchronizationOfFile.synchronizationOfFile.repository.MemberRepository;

@Controller
public class UserController {


    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ConnectListRepository connectListRepository;

    @PostMapping("create")
    public String create(@RequestParam("name") String name, @RequestParam("password") String password, Model model) {
        // 접속 멤버 DB에 추가해주기
        Member user = new Member();
        user.setName(name);
        user.setPassword(password);

        System.out.println("name : " + user.getName() + " / password : " + user.getPassword());
        memberRepository.save(user);

        //  가입한 멤버 모두 찾아 return 하기
        // model.addAttribute("user", userRepository.findAll());

        return "home";
    }

    @PostMapping("login")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model){

        Member findUser = memberRepository.findByName(name);

        if(findUser == null){
            if(!findUser.getPassword().equals(password)) {
                // 로그인 성공시 main으로 보내주기
                return "main";
            }
        }

        return "home";

        // 로그인 실패 시 home으로 보내주기
//        return"<script>alert('일치하는 정보가 없습니다.');location.href='logout.do';</script>";
    }
}
