package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // Bean 직접 등록

    // MemberService, MemberRepository 둘 다 Bean에 등록을 한다.
    // Bean에 등록이 되어있는 memberRepository를 생성자에 넣어준다.
    // MemberService -> MemberRepository

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
