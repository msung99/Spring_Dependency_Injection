package com.example.demo;

import com.example.demo.Order.OrderService;
import com.example.demo.member.Grade;
import com.example.demo.member.Member;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        // ApplicationContext : "스프링 컨테이너" 라고 부르며, 인터페이스이다. 그에 대한 구현체중 하나가  AnnotationConfigApplicationContext 이다.
        // AppConfig 를 생성자의 파라미터로 넣어주면서, 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든것이다.

        // 1. AnnotationConfigApplicationContext 에 Appconfig 클래스의 정보를 줌으로써, 스프링 컨테이너가 생성되고
        // 2. 스프링 컨테이너안에 스프링 빈(bean) 저장소라는 것이 있는데, 빈 저장소안에 Appconfig 클래스 정보를 활용해서
        //  빈 저장소에 구성성분들을 구성한다. 이떄 구성성분들은 "빈 이름 - 빈 객체" 쌍으로 이루어진다.
        // => 빈 이름 : 메소드 이름, 빈 객체 : 해당 메소드를 리턴했을 때 받는 객체
        // 빈 저장소에 빈 이름에는 메소드 이름이 들어가고, 빈 객체는 AppConfig.class 를 호출해서 각 메소드로부터 객체를 리턴받는다.

        // 스프링 컨네티어에 쭉 빈(bean) 객체를 모두 생성하고 나서, 각 빈 객체끼리 의존관계를 주입한다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("OrderService", OrderService.class);

        //MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember =  memberService.findMember(1L);
        System.out.println("findMember = " + findMember.getName());
        System.out.println("new member = " + member.getName());
    }
}
