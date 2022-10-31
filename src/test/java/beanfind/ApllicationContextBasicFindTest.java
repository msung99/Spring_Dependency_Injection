package beanfind;

import com.example.demo.AppConfig;
import com.example.demo.member.MemberService;
import com.example.demo.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApllicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class); // 이렇게 MemberService 같은 인터페이스로 조회하면 그에 대한
        // 구현체가 조회의 대상이 된다.
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class); // memberService 객체가 MemberServiceImpl 의 인스턴스이면 성공
        //System.out.println("memberService = " + memberService);
        //System.out.println("memberService.getClass() = " + memberService.getClass());
    }

    @Test
    @DisplayName("빈 이름없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        // 스프링 컨테이너에 MemberServiceImpl 라는 구현체가 등록되어 있으면 테스트 성공!
        // => 꼭 인터페이스가 아닌 구현체로 조회해도 된다. 왜냐하면 스프링 빈에 등록되 인스턴스 타입을 보고 결정하기 때문이다!
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //ac.getBean("xxxxx", MemberService.class);
        //MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class));
        //  assertThrows : 예외가 던져져야함. 오른쪽에 있는 "() -> ac.getBean("xxxxx", MemberService.class));" 로직을 실행하면 예외가 터저야지 테스트가 성공한다!
    }
}
