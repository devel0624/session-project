package com.nhnacademy.sessionproject.service;

import com.nhnacademy.sessionproject.dto.LoginRequest;
import com.nhnacademy.sessionproject.entity.Member;
import com.nhnacademy.sessionproject.repository.MemberRepository;
import com.nhnacademy.sessionproject.session.Session;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final MemberRepository memberRepository;

    public LoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean login(LoginRequest request){
        Optional<Member> member = memberRepository.findMemberById(request.getId());

        return member.map(value -> value.getPassword().equals(request.getPassword())).orElse(false);

    }

}
