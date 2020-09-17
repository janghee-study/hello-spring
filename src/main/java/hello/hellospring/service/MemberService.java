package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { //외부에서 직접만들어주도록 변경

        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){

        long start = System.currentTimeMillis();

        try{
            //같은 이름이 있는 중복회원X
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();       //AOP를 사용하면 이부분들만 남기고 지울수있음
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }
    //위에를 리팩토링해서 새롭게 만들어줌 ctrl+alt+shift+t
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
            throw new IllegalStateException(("이미 존재하는 회원입니다."));
        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    public  Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }
}
