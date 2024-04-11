package com.task_management_system.Service;

import com.task_management_system.Entity.Member;
import com.task_management_system.Entity.Task;
import com.task_management_system.Enum.Status;
import com.task_management_system.Exception.MemberByIdNotFoundException;
import com.task_management_system.Exception.MemberByNameAlreadyExistException;
import com.task_management_system.Repository.DAO.MemberDAO;
import com.task_management_system.Request.Member.MemberCreateRequest;
import com.task_management_system.Request.Member.MemberUpdateRequest;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberDAO memberDAO;

    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    public Iterable<Member> list() {
        return this.memberDAO.findAll();
    }

    public Member create(MemberCreateRequest request) throws Exception {
        this.checkReceivedName(request.getName());

        Member member = Member.builder()
            .name(request.getName())
            .tasks(new ArrayList<>())
            .build()
        ;
        this.memberDAO.add(member);

        return member;
    }

    public Member read(UUID id) throws Exception {
        return this.memberDAO.find(id).orElseThrow(() -> new MemberByIdNotFoundException(id));
    }

    public Member update(Member member, MemberUpdateRequest request) throws Exception {
        if (null != request.getName()) {
            this.checkReceivedName(request.getName(), member.getId());
        }

        member.setName(request.getName());

        return member;
    }

    public void delete(Member member) {
        this.memberDAO.remove(member);
    }

    public Map<String, Status> getTaskStatuesByMember(Member member) {
        return member.getTasks()
            .stream()
            .collect(Collectors.toMap(Task::getName, Task::getStatus))
        ;
    }

    private void checkReceivedName(String name) throws Exception {
        if (this.memberDAO.isExistByName(name)) {
            throw new MemberByNameAlreadyExistException(name);
        }
    }

    private void checkReceivedName(String name, UUID id) throws Exception {
        if (this.memberDAO.isExistByName(name, id)) {
            throw new MemberByNameAlreadyExistException(name);
        }
    }
}