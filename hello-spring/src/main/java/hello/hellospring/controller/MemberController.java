package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // Bean에 등록된 memberService를 넣어준다.
    /* 생성자 주입 */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
