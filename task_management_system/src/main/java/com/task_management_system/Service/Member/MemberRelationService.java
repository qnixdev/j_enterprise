package com.task_management_system.Service.Member;

import com.task_management_system.Entity.Member;
import com.task_management_system.Entity.Task;
import com.task_management_system.Enum.Status;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberRelationService {
    public Map<String, Status> getTaskStatuesByMember(Member member) {
        return member.getTasks()
            .stream()
            .collect(Collectors.toMap(Task::getName, Task::getStatus))
        ;
    }
}