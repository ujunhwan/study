package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    // bean에 등록된 서비스를 가져오기 위해
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
