package com.java_enterprise.Controller.Member;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Request.Member.MemberCreateRequest;
import com.java_enterprise.Request.Member.MemberUpdateRequest;
import com.java_enterprise.Service.Member.MemberCrudService;
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