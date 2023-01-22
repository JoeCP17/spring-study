package com.inflearn.datajpa;

import com.inflearn.datajpa.dto.MemberDTO;
import com.inflearn.datajpa.entity.Member;
import com.inflearn.datajpa.entity.Member_v2;
import com.inflearn.datajpa.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/members/{id}")
    public String findMember(@PathVariable("id") Long id) {
        Member_v2 member = memberRepository.findById(id).get();
        return member.getUserName();
    }

    /*
        조회 용으로만 사용해야 한다.
        도메인 클래스 컨버터의 경우 간단한 조회용으로는 사용해도 괜찮으나 그 이상으로 사용하는건 권장하지 않는다.
     */
    @GetMapping("/members2/{id}")
    public String findMember_v2(@PathVariable("id") Member_v2 member) {
        return member.getUserName();
    }

    @GetMapping("/members")
    public Page<MemberDTO> list(@PageableDefault(size = 5, sort = "userName") Pageable pageable) {
        Page<Member_v2> members = memberRepository.findAll(pageable);
        return members.<MemberDTO>map(member -> new MemberDTO(member.getId(), member.getUserName(), null));
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 100; i++) {
            memberRepository.save(new Member_v2("user" + i, i));
        }
    }
}
