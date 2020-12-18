package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    /* DiscountPolicy interface 뿐만 아니라, 구현체에도 의존하고 있다. -> DIP 위반 */
    /* FixDiscountPolicy를 RateDiscountPolicy로 변경하려면, OrderServiceImpl의 코드도 변경해야 한다. -> OCP 위반 */

    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /* interface에만 의존하도록 코드 변경 -> DIP 준수 */
    //private DiscountPolicy discountPolicy;

    /* 생성자 주입 */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // SRP가 잘 구현됐다 why? OrderService에서는 discount에 관한건 알 수 없음
        // DiscountPolicy의 함수의 결과만 받음
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
