package com.example.demo;

import com.example.demo.Order.OrderService;
import com.example.demo.Order.OrderServiceImpl;
import com.example.demo.discount.DiscountPolicy;
import com.example.demo.discount.FixDiscountPolicy;
import com.example.demo.discount.RateDiscountPolicy;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import com.example.demo.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Appconfig : 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
// => 마치 "기획자" 의 역할을 수행하고, AppConfig 덕분에 DIP 의존관계를 지킬 수 있게 되었다.
//    이제는 실제 구현체가 아닌, 인터페이스에만 의존할 수 있게 되었다.
// => 생성자 주입 : 생 성한 객체 인스턴스의 참조(레퍼런스)를 "생성자를 통해서 주입(연결)" 해준다.

// 객체를 생성하고 연결하는 역할과 실횅하는 역할이 명확하게 분리가 되었다!
@Configuration
public class AppConfig {
    // AppConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl 을 생성하면서 생성자로 전달한다.

    // 애플리케이션에 적용시킬 구현체가 변경될때, AppConfig 의 생성자 주입 부분만 딱 1줄만 수정하면 된다!
    @Bean
    public MemberService memberService() { // 생성자 주입
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public static MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService OrderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy(); // Appconfig 에서 할인정책 역할을 담당하는 구현을 FixDiscountPolicy -> RateDiscountPolicy 객체로 변경했다.
        // 이제 어떤 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig 만 변경하면 된다.
    }
}
