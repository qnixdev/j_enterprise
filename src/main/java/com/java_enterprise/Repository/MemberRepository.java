package com.java_enterprise.Repository;

import com.java_enterprise.Entity.Member;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class MemberRepository {
    private final Map<Long, Member> members = new HashMap<>();

    public Long nextId() {
        var nextId = this.members.keySet().size();

        return (long) ++nextId;
    }

    public void add(Member member) {
        if (!this.members.containsKey(member.getId())) {
            this.members.put(member.getId(), member);
        }
    }

    public void remove(Member member) {
        this.members.remove(member.getId());
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(this.members.get(id));
    }

    public Iterable<Member> findAll() {
        return this.members.values();
    }

    public boolean isExistByName(String name) {
        return this.members.values()
            .stream()
            .anyMatch(m -> m.getName().equals(name))
        ;
    }

    public boolean isExistByName(String name, Long existMemberId) {
        return this.members.values()
            .stream()
            .anyMatch(m -> m.getName().equals(name) && !Objects.equals(m.getId(), existMemberId))
        ;
    }
}