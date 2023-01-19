package com.nhnacademy.sessionproject.repository;

import com.nhnacademy.sessionproject.entity.Member;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 * DB를 세션으로 사용하기 때문에 DB를 대체할 메모리 레포지토리를 생성
 */

@Repository
public class MemberRepository {

    private final Map<String, Member> members;

    public MemberRepository() {
        this.members = new HashMap<>();

        members.put("normal",new Member("normal","password"));
        members.put("admin",new Member("admin","password"));
    }

    public Optional<Member> findMemberById(String id) {
        return Optional.ofNullable(members.get(id));
    }

}
