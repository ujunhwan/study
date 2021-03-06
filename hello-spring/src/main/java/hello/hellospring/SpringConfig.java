package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    /*
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    */

    // Bean 직접 등록

    // MemberService, MemberRepository 둘 다 Bean에 등록을 한다.
    // Bean에 등록이 되어있는 memberRepository를 생성자에 넣어준다.
    // MemberService -> MemberRepository

    // service에서 autowired 안써도됨 여기서 주입되기 때문
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        // DI 사용의 이점. 기존의 코드를 손대지 않고, Config 만으로 구현 클래스를 변경할 수 있음
        // 이 부분만 수정하면 변수 저장에서 DB 저장으로 간편하게 바꿀 수 있다
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

    /*
    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
    */
}
