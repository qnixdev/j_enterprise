package com.task_management_system.Controller.Member;

import com.task_management_system.Entity.Member;
import com.task_management_system.Service.Member.MemberCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberListController {
    private final MemberCrudService memberCrudService;

    @Autowired
    public MemberListController(MemberCrudService memberCrudService) {
        this.memberCrudService = memberCrudService;
    }

    @GetMapping
    public Iterable<Member> listAll() {
        return this.memberCrudService.list();
    }
}