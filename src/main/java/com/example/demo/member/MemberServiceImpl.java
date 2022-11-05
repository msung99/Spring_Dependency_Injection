package com.example.demo.member;


// DIP 의존관계에 대한 문제점은 외부(AppConfig) 에만 맡기고, 오로지 실행해만 집중하면 된다.
// => MemberServiceImpl 은 MemberRepository 인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.

// 클라이언트인 memberServiceImpl 입장에서 보면, 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection), 즉 의존관계 주입 또는 의존성 주입이라고 한다.

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 컴포넌트를 사용하면 MemberServiceImpl 을 스프링 빈으로 자동으로 들록해주고, MemberRepository 타입에 맞는 스프링 빈을 자동으로 주입해준다
// 정리 : @Component : 스프링 빈으로 자동 등록해줌 (=> @Bean 키워드로 일일이 다 노가다로 스프링 빈으로 등록해줄 필요가 없어짐)
// @Autowired : 생성자 쪽에 이 키워드를 붙이면, 자동으로 의존관계를 주입해줌
@Component
public class MemberServiceImpl implements MemberService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    // AutoWired 기능을 생성자에 붙여주면 스프링이 MemberRepository 를 만든놈을 찾아와서 자동으로 의존관계 주입을 해준다.
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
