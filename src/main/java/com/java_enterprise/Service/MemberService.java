package com.java_enterprise.Service;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Entity.Task;
import com.java_enterprise.Enum.Status;
import com.java_enterprise.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Iterable<Member> list() {
        return this.memberRepository.findAll();
    }

    public Member create(String name) {
        Member member = new Member();
        member.setId(this.memberRepository.nextId());
        member.setName(name);

        this.memberRepository.add(member);

        return member;
    }

    public Member read(Long id) {
        return this.memberRepository
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException(String.format("Member with id: %d not found!", id)))
        ;
    }

    public void removeMember(Member member) {
        this.memberRepository.remove(member);
    }

    public void delegateTask(Task task, Member newMember) {
        var existMember = task.getMember();

        if (existMember != newMember) {
            existMember.getTasks().remove(task);
            newMember.getTasks().add(task);
            task.setMember(newMember);
        }
    }

    public Map<String, Status> getTaskStatuesByMember(Member member) {
        return member.getTasks()
            .stream()
            .collect(Collectors.toMap(Task::getName, Task::getStatus))
        ;
    }
}