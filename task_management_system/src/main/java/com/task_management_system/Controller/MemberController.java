package com.task_management_system.Controller;

import com.task_management_system.Entity.Member;
import com.task_management_system.Enum.Status;
import com.task_management_system.Request.Member.MemberCreateRequest;
import com.task_management_system.Request.Member.MemberUpdateRequest;
import com.task_management_system.Service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/list")
    public Iterable<Member> listAll() {
        return this.memberService.list();
    }

    @PostMapping
    public ResponseEntity<Member> create(@Valid @RequestBody MemberCreateRequest request) throws Exception {
        var member = this.memberService.create(request);

        return ResponseEntity.ok(member);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> read(@PathVariable UUID id) throws Exception {
        var member = this.memberService.read(id);

        return ResponseEntity.ok(member);
    }

    @PutMapping
    public ResponseEntity<Member> update(@Valid @RequestBody MemberUpdateRequest request) throws Exception {
        var member = this.memberService.read(request.getId());

        return ResponseEntity.ok(this.memberService.update(member, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) throws Exception {
        var member = this.memberService.read(id);
        this.memberService.delete(member);

        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}/task-status")
    public ResponseEntity<Map<String, Status>> getMemberTaskStatusMap(@PathVariable UUID id) throws Exception {
        Member member = this.memberService.read(id);

        return ResponseEntity.ok(this.memberService.getTaskStatuesByMember(member));
    }
}