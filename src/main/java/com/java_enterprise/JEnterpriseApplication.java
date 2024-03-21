package com.java_enterprise;

import com.java_enterprise.Entity.Member;
import com.java_enterprise.Entity.Task;
import com.java_enterprise.Enum.Priority;
import com.java_enterprise.Enum.Status;
import com.java_enterprise.Service.MemberService;
import com.java_enterprise.Service.TaskService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import java.util.*;

@SpringBootApplication
public class JEnterpriseApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JEnterpriseApplication.class, args);

        // Init objects
        var memberService = context.getBean(MemberService.class);
        var taskService = context.getBean(TaskService.class);

        List<Task> taskList = initTastList(taskService);

        final String[] names = {"William", "Amelia", "Michael", "Jack", "Nick"};
        int index = 0;

        for (String name : names) {
            Member member = memberService.create(name);

            for (int i = 0; i < 20; i++) {
                var task = taskList.get(index);

                member.getTasks().add(task);
                task.setMember(member);
                index++;
            }
        }
    }

    public static List<Task> initTastList(TaskService taskService) {
        List<Task> tasks = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            var date = Calendar.getInstance();
            date.setTime(new Date());
            date.add(Calendar.DAY_OF_MONTH, new Random().nextInt(62));

            var task = taskService.create(
                String.format("Task_%d", i),
                String.format("Description_%d", i),
                Status.getRandomStatus(),
                Priority.getRandomPriority(),
                date.getTime()
            );

            if (task.getStatus() == Status.DONE) {
                task.setDateDeadlineAt(new Date());
            }

            tasks.add(task);
        }

        return tasks;
    }
}