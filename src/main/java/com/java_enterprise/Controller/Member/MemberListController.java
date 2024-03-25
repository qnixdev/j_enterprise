package com.java_enterprise.Controller.Member;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Service.Member.MemberCrudService;
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