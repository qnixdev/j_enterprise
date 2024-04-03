package com.task_management_system.Controller;

import com.task_management_system.Entity.Member;
import com.task_management_system.Enum.Status;
import com.task_management_system.Request.Member.MemberCreateRequest;
import com.task_management_system.Request.Member.MemberUpdateRequest;
import com.task_management_system.Service.Member.MemberCrudService;
import com.task_management_system.Service.Member.MemberRelationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberCrudService memberCrudService;
    private final MemberRelationService memberRelationService;

    public MemberController(
        MemberCrudService memberCrudService,
        MemberRelationService memberRelationService
    ) {
        this.memberCrudService = memberCrudService;
        this.memberRelationService = memberRelationService;
    }

    @GetMapping("/list")
    public Iterable<Member> listAll() {
        return this.memberCrudService.list();
    }

    @GetMapping("/{id}/task-status")
    public ResponseEntity<Map<String, Status>> getMemberTaskStatusMap(@PathVariable Long id) throws Exception {
        Member member = this.memberCrudService.read(id);

        return ResponseEntity.ok(this.memberRelationService.getTaskStatuesByMember(member));
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