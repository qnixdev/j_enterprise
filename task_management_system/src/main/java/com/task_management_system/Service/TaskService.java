package com.task_management_system.Service;

import com.task_management_system.Entity.Member;
import com.task_management_system.Entity.Task;
import com.task_management_system.Exception.TaskByIdNotFoundException;
import com.task_management_system.Exception.TaskByNameAlreadyExistException;
import com.task_management_system.Repository.AsTaskRepository;
import com.task_management_system.Request.Task.TaskCreateRequest;
import com.task_management_system.Request.Task.TaskDelegateRequest;
import com.task_management_system.Request.Task.TaskStatusRequest;
import com.task_management_system.Request.Task.TaskUpdateRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class TaskService {
    private final MemberService memberService;
    private final AsTaskRepository taskRepository;

    public TaskService(
        MemberService memberService,
        AsTaskRepository taskRepository
    ) {
        this.memberService = memberService;
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> list() {
        return this.taskRepository.findAll();
    }

    public Task create(TaskCreateRequest request) throws Exception {
        this.checkReceivedName(request.getName());

        var task = Task.builder()
            .name(request.getName())
            .description(request.getDescription())
            .status(request.getStatus())
            .priority(request.getPriority())
            .dateDeadlineAt(request.getDeadline())
            .build()
        ;

        if (null != request.getMemberId()) {
            Member member = this.memberService.read(request.getMemberId());
            member.getTasks().add(task);
            task.setMember(member);
        }

        this.taskRepository.save(task);

        return task;
    }

    public Task read(UUID id) throws Exception {
        return this.taskRepository.findById(id).orElseThrow(() -> new TaskByIdNotFoundException(id));
    }

    public Task update(Task task, TaskUpdateRequest request) throws Exception {
        if (null != request.getName()) {
            this.checkReceivedName(request.getName(), task.getId());
        }

        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDateDeadlineAt(request.getDeadline());

        if (null != request.getMemberId()) {
            Member member = this.memberService.read(request.getMemberId());
            member.getTasks().add(task);
            task.getMember().getTasks().remove(task);
            task.setMember(member);
        }

        return task;
    }

    public void delete(Task task) {
        this.taskRepository.delete(task);
    }

    public Task delegate(TaskDelegateRequest request) throws Exception {
        Task task = this.read(request.getTaskId());
        Member member = this.memberService.read(request.getMemberId());

        task.getMember().getTasks().remove(task);
        member.getTasks().add(task);
        task.setMember(member);

        return task;
    }

    public Task changeStatus(TaskStatusRequest request) throws Exception {
        Task task = this.read(request.getTaskId());
        task.setStatus(request.getStatus());

        return task;
    }

    private void checkReceivedName(String name) throws Exception {
        if (this.taskRepository.isExistByName(name)) {
            throw new TaskByNameAlreadyExistException(name);
        }
    }

    private void checkReceivedName(String name, UUID id) throws Exception {
        if (this.taskRepository.isExistByName(name, id)) {
            throw new TaskByNameAlreadyExistException(name);
        }
    }
}