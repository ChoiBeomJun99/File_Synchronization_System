package synchronizationOfFile.synchronizationOfFile.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import synchronizationOfFile.synchronizationOfFile.domain.ConnectList;
import synchronizationOfFile.synchronizationOfFile.domain.Member;
import synchronizationOfFile.synchronizationOfFile.repository.ConnectListRepository;
import synchronizationOfFile.synchronizationOfFile.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ConnectListRepository connectListRepository;

    @PostMapping("create")
    public String create(HttpServletResponse response, @RequestParam("name") String name, @RequestParam("password") String password, Model model) throws Exception {
        Member tmp = memberRepository.findByName(name);

        if (tmp != null) {
            // 만약 생성하고자 하는 멤버의 이름이 중복이라면 가입 절차 중단
            ScriptUtils.alertAndMovePage(response, "해당 이름은 중복입니다.", "/create");
            return null;
        }

        // 이름 중복 검사 이후
        // 접속 멤버 DB에 추가해주기
        Member user = new Member();
        user.setName(name);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(password);

        memberRepository.save(user);
        ScriptUtils.alertAndMovePage(response, "회원가입이 완료되었습니다." , "/home");

        return "home";
    }

    @PostMapping("login")
    public String login(HttpServletResponse response, @RequestParam("name") String name, @RequestParam("password") String password, Model model, RedirectAttributes re) throws Exception{

        Member findUser = memberRepository.findByName(name);

        if(findUser != null) {
            if(findUser.getPassword().equals(password)) {
                ConnectList tmp = connectListRepository.findByName(name);

                if (tmp == null) {
                    // 기존에 접속 유저가 아니었기에 접속 데이터에 추가
                    ConnectList user = new ConnectList();
                    user.setName(name);
                    user.setConnectedAt(LocalDateTime.now());
                    connectListRepository.save(user);
                }

                re.addAttribute("memberId", findUser.getId());

                // 로그인 성공시 main 으로 보내주기
                return "redirect:/main";
            }
            System.out.println("비밀번호가 일치하지 않은 상황 : " + password + " / " + findUser.getPassword());
            ScriptUtils.alertAndMovePage(response, "비밀번호가 일치하지 않습니다.", "/login");
        } else {
            System.out.println("해당 아이디와 비밀번호의 사용자는 존재하지 않음 : " + name + " / " + password);
            ScriptUtils.alertAndMovePage(response, "존재하지 않은 회원입니다.", "/login");

        }
        // 로그인 실패 시 home 으로 보내주기
        return "home";
    }

    @PostMapping("disconnect")
    public String disconnect(HttpServletRequest req){
        String referer = req.getHeader("REFERER");

        String[] referArray = referer.split("=");
        Long memberId = Long.valueOf(referArray[1]);

        Optional<ConnectList> tmp = connectListRepository.findById(memberId);

        if( tmp != null ) {
            // connectList 에 tmp라는 데이터가 존재한다면 이를 접속해지하는 기능을 위해 connectList에서 remove 해준다.
            connectListRepository.deleteById(memberId);
        }

        return "redirect:/home";
    }


}
