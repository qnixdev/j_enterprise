package com.task_management_system.Controller.Member;

import com.task_management_system.Entity.Member;
import com.task_management_system.Enum.Status;
import com.task_management_system.Service.Member.MemberCrudService;
import com.task_management_system.Service.Member.MemberRelationService;
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