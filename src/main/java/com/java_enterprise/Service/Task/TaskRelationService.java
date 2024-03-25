package com.java_enterprise.Service.Task;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Entity.Task;
import com.java_enterprise.Request.Task.TaskDelegateRequest;
import com.java_enterprise.Request.Task.TaskStatusRequest;
import com.java_enterprise.Service.Member.MemberCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskRelationService {
    private final MemberCrudService memberCrudService;
    private final TaskCrudService taskCrudService;

    @Autowired
    public TaskRelationService(
        MemberCrudService memberCrudService,
        TaskCrudService taskCrudService
    ) {
        this.memberCrudService = memberCrudService;
        this.taskCrudService = taskCrudService;
    }

    public Task delegate(TaskDelegateRequest request) throws Exception {
        Task task = this.taskCrudService.read(request.getTaskId());
        Member member = this.memberCrudService.read(request.getMemberId());

        task.getMember().getTasks().remove(task);
        member.getTasks().add(task);
        task.setMember(member);

        return task;
    }

    public Task changeStatus(TaskStatusRequest request) throws Exception {
        Task task = this.taskCrudService.read(request.getTaskId());
        task.setStatus(request.getStatus());

        return task;
    }
}