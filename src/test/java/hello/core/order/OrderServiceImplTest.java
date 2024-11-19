package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    MemberRepository memberRepository;

    @Mock
    DiscountPolicy discountPolicy;


    @Test
    void createOrder() {
        Member member = new Member(1L, "memberA", Grade.VIP);
        when(memberRepository.findById(1L)).thenReturn(member);
        when(discountPolicy.discount(member, 10000)).thenReturn(2000);
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, discountPolicy);
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(2000);
    }

}