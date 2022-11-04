package com.example.demo.singleton;

import com.example.demo.AppConfig;
import com.example.demo.Order.OrderServiceImpl;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfiguarationSingletonTest {

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // memberRepository 인스턴스 객체는 3개에서 모두 같은 인스턴스 객체가 공유되어 사용된다.
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("OrderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository" + memberRepository1);
        System.out.println("orderService -> memberRepository" + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    void configurationDeep(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean = " + bean.getClass());
    }
}
