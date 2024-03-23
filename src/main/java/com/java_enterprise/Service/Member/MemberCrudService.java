package com.java_enterprise.Service.Member;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Exception.MemberByIdNotFoundException;
import com.java_enterprise.Exception.MemberByNameAlreadyExistException;
import com.java_enterprise.Repository.MemberRepository;
import com.java_enterprise.Request.Member.MemberCreateRequest;
import com.java_enterprise.Request.Member.MemberUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class MemberCrudService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberCrudService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Iterable<Member> list() {
        return this.memberRepository.findAll();
    }

    public Member create(MemberCreateRequest request) throws Exception {
        this.checkReceivedName(request.getName());

        Member member = Member.builder()
            .id(this.memberRepository.nextId())
            .name(request.getName())
            .tasks(new ArrayList<>())
            .build()
        ;
        this.memberRepository.add(member);

        return member;
    }

    public Member read(Long id) throws Exception {
        return this.memberRepository
            .findById(id)
            .orElseThrow(() -> new MemberByIdNotFoundException(id))
        ;
    }

    public Member update(Member member, MemberUpdateRequest request) throws Exception {
        if (null != request.getName()) {
            this.checkReceivedName(request.getName(), member.getId());
        }

        member.setName(request.getName());

        return member;
    }

    public void delete(Member member) {
        this.memberRepository.remove(member);
    }

    private void checkReceivedName(String name) throws Exception {
        if (this.memberRepository.isExistByName(name)) {
            throw new MemberByNameAlreadyExistException(name);
        }
    }

    private void checkReceivedName(String name, Long id) throws Exception {
        if (this.memberRepository.isExistByName(name, id)) {
            throw new MemberByNameAlreadyExistException(name);
        }
    }
}