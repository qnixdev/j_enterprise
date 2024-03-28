package com.task_management_system.Service.Task;

import com.task_management_system.Entity.Member;
import com.task_management_system.Entity.Task;
import com.task_management_system.Request.Task.TaskDelegateRequest;
import com.task_management_system.Request.Task.TaskStatusRequest;
import com.task_management_system.Service.Member.MemberCrudService;
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