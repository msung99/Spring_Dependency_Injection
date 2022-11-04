package com.example.demo.member;


// DIP 의존관계에 대한 문제점은 외부(AppConfig) 에만 맡기고, 오로지 실행해만 집중하면 된다.
// => MemberServiceImpl 은 MemberRepository 인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.

// 클라이언트인 memberServiceImpl 입장에서 보면, 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection), 즉 의존관계 주입 또는 의존성 주입이라고 한다.
public class MemberServiceImpl implements MemberService{
    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

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
