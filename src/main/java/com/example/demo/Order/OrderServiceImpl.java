package com.example.demo.Order;

import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
// @RequiredArgsConstructor // lombok 의 기능 => 생성자를 자동생성. 이 기능을 사용하면 final이 붙은 필드를 모아서 "생성자룰 자동으로 만들어준다"
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository; // = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy; // = new FixDiscountPolicy();
    // DIP 의존관계를 만족 => 구현체에도 의존하던 클라이언트가, 이제는
    // 인터페이스에만 의존하도록 변경되었다.
    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    */

    // OrderServiceImpl 입장에서는 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    // 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig) 에서 결정한다.

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy")  DiscountPolicy rateDiscountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }


    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice){
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
