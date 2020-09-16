package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) { //외부에서 직접만들어주도록 변경

        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름이 있는 중복회원X
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    //위에를 리팩토링해서 새롭게 만들어줌 ctrl+alt+shift+t
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
            throw new IllegalStateException(("이미존재하는 회원입니다."));
        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
         return memberRepository.findAll();
    }

    public  Optional<Member> findOne(Long memberId){

        return memberRepository.findById(memberId);
    }
}
