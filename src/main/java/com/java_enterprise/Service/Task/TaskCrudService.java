package com.java_enterprise.Service.Task;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Entity.Task;
import com.java_enterprise.Exception.TaskByIdNotFoundException;
import com.java_enterprise.Exception.TaskByNameAlreadyExistException;
import com.java_enterprise.Repository.TaskRepository;
import com.java_enterprise.Request.Task.TaskCreateRequest;
import com.java_enterprise.Request.Task.TaskUpdateRequest;
import com.java_enterprise.Service.Member.MemberCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskCrudService {
    private final MemberCrudService memberCrudService;
    private final TaskRepository taskRepository;

    @Autowired
    public TaskCrudService(
        MemberCrudService memberCrudService,
        TaskRepository taskRepository
    ) {
        this.memberCrudService = memberCrudService;
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> list() {
        return this.taskRepository.findAll();
    }

    public Task create(TaskCreateRequest request) throws Exception {
        this.checkReceivedName(request.getName());

        var task = Task.builder()
            .id(this.taskRepository.nextId())
            .name(request.getName())
            .description(request.getDescription())
            .status(request.getStatus())
            .priority(request.getPriority())
            .dateDeadlineAt(request.getDeadline())
            .build()
        ;

        if (null != request.getMemberId()) {
            Member member = this.memberCrudService.read(request.getMemberId());
            member.getTasks().add(task);
            task.setMember(member);
        }

        this.taskRepository.add(task);

        return task;
    }

    public Task read(Long id) throws Exception {
        return this.taskRepository
            .findById(id)
            .orElseThrow(() -> new TaskByIdNotFoundException(id))
        ;
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
            Member member = this.memberCrudService.read(request.getMemberId());
            member.getTasks().add(task);
            task.getMember().getTasks().remove(task);
            task.setMember(member);
        }

        return task;
    }

    public void delete(Task task) {
        this.taskRepository.remove(task);
    }

    private void checkReceivedName(String name) throws Exception {
        if (this.taskRepository.isExistByName(name)) {
            throw new TaskByNameAlreadyExistException(name);
        }
    }

    private void checkReceivedName(String name, Long id) throws Exception {
        if (this.taskRepository.isExistByName(name, id)) {
            throw new TaskByNameAlreadyExistException(name);
        }
    }
}