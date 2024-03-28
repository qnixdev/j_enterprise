package com.task_management_system.Service.Task;

import com.task_management_system.Entity.Member;
import com.task_management_system.Entity.Task;
import com.task_management_system.Exception.TaskByIdNotFoundException;
import com.task_management_system.Exception.TaskByNameAlreadyExistException;
import com.task_management_system.Repository.TaskRepository;
import com.task_management_system.Request.Task.TaskCreateRequest;
import com.task_management_system.Request.Task.TaskUpdateRequest;
import com.task_management_system.Service.Member.MemberCrudService;
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