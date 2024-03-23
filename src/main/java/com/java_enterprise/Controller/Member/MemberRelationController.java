package com.java_enterprise.Controller.Member;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Enum.Status;
import com.java_enterprise.Service.Member.MemberCrudService;
import com.java_enterprise.Service.Member.MemberRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberRelationController {
    private final MemberCrudService memberCrudService;
    private final MemberRelationService memberRelationService;

    @Autowired
    public MemberRelationController(
        MemberCrudService memberCrudService,
        MemberRelationService memberRelationService
    ) {
        this.memberCrudService = memberCrudService;
        this.memberRelationService = memberRelationService;
    }

    @GetMapping("/{id}/task-status")
    public ResponseEntity<Map<String, Status>> getMemberTaskStatusMap(@PathVariable Long id) throws Exception {
        Member member = this.memberCrudService.read(id);

        return ResponseEntity.ok(this.memberRelationService.getTaskStatuesByMember(member));
    }
}