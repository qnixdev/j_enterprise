package com.task_management_system.Controller.Member;

import com.task_management_system.Entity.Member;
import com.task_management_system.Request.Member.MemberCreateRequest;
import com.task_management_system.Request.Member.MemberUpdateRequest;
import com.task_management_system.Service.Member.MemberCrudService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberCrudController {
    private final MemberCrudService memberCrudService;

    @Autowired
    public MemberCrudController(MemberCrudService memberCrudService) {
        this.memberCrudService = memberCrudService;
    }

    @PostMapping
    public ResponseEntity<Member> create(@Valid @RequestBody MemberCreateRequest request) throws Exception {
        var member = this.memberCrudService.create(request);

        return ResponseEntity.ok(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> read(@PathVariable Long id) throws Exception {
        var member = this.memberCrudService.read(id);

        return ResponseEntity.ok(member);
    }

    @PutMapping
    public ResponseEntity<Member> update(@Valid @RequestBody MemberUpdateRequest request) throws Exception {
        var member = this.memberCrudService.read(request.getId());

        return ResponseEntity.ok(this.memberCrudService.update(member, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) throws Exception {
        var member = this.memberCrudService.read(id);
        this.memberCrudService.delete(member);

        return ResponseEntity.ok(true);
    }
}