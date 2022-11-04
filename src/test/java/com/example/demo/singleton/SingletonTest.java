package com.example.demo.singleton;

import com.example.demo.AppConfig;
import com.example.demo.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// 싱글톤 패턴을 적용하면 고객의 요청이 들어올 때 마다 객체를 생성하는 것이 아니라,
// 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다. 하지만 싱글톤 패턴을 다음과 같은 수 많은 문제점들을 가지고 있다.

// 1. 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
// 2. 의존관계상 클라이언트가 구체 클래스에 의존한다 (DIP 를 위반한다)
// 3. 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
// 4. 내부 속성을 변경하거나 초기화하기 어렵다.
// 5. private 생성자로 자식 클래스를 만들기 어렵다
// 6. 결론젇으로 유연성이 떨어진다.


public class SingletonTest {

    // 이전에 만듷었던 스프링 없는 순수한 DI 컨테이너인 AppConfig 는 요청을 할떄마다 객체를 새로 생성한다.
    // => 싱글톤 패턴 : 해결방안임. 곡객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸되는 메모리 공간 문제가 있는데,
    // 싱글톤 패턴을 활용해 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다.
    // 즉, 싱글톤 패턴이란 클래스 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다.
    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer(){
        // => Appconfig 한테 memberSerivce 를 주라고 할떄마다(호출 할때마다) 다른 객체가 계속 생성된다.
        AppConfig appConfig = new AppConfig();
        // 1. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        // 3. 참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 진짜로 참조값(객체) 가 다른지 다시한번 검증
        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        //new SingletonService(); => 싱글톤은 객체 하나만 생성가능! 생성자를 private 으로 만들어서 외부에서 생성을 막음
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        // 모든 객체가 공유하는 같은 싱글톤 인스턴스 객체가 리턴(출력) 된다.
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        Assertions.assertThat(singletonService1).isSameAs(singletonService2);
    }

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void springContainer(){
        ApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        // 참조값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 진짜로 참조값(객체) 가 다른지 다시한번 검증
        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
