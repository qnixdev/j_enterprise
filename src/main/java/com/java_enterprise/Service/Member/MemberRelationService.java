package com.java_enterprise.Service.Member;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Entity.Task;
import com.java_enterprise.Enum.Status;
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