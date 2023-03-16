package com.ll.basic1.boundedContext.member.service;

import com.ll.basic1.base.rsData.RsData;
import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public RsData tryLogin(String username, String password) {
        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        if (!member.getPassword().equals(password)) {
            return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
        }

        return RsData.of("S-1", "%s 님 환영합니다.".formatted(username), member.getId());
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Member findById(long id) {
        return memberRepository.findById(id);
    }

        /*
        memList.add(new Member("user1", "1234"));
        memList.add(new Member("abc", "12345"));
        memList.add(new Member("test", "12346"));
        memList.add(new Member("love", "12347"));
        memList.add(new Member("like", "12348"));
        memList.add(new Member("giving", "12349"));
        memList.add(new Member("thanks", "123410"));
        memList.add(new Member("hello", "123411"));
        memList.add(new Member("good", "123412"));
        memList.add(new Member("peace", "123413"));
        */

        /*
        if (!password.equals("1234")) {
            return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
        } else if (!username.equals("user1")) {
            return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        return RsData.of("S-1", "%s 님 환영합니다.".formatted(username));
        */
}

