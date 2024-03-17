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
import java.util.stream.StreamSupport;

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
                member.addTask(taskList.get(index));
                index++;
            }
        }

        var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        var datePlusSevenDays = calendar.getTime();

        // Operations
        //get members by id
        var member1 = memberService.read(1L);
        var member2 = memberService.read(2L);
        System.out.println(member1.getId());
        System.out.println(member2.getId());

        //add new task to member
        var newTask = taskService.create(
            "Task_555",
            "Description_555",
            Status.CREATED,
            Priority.NORMAL,
            datePlusSevenDays
        );
        member2.addTask(newTask);
        System.out.println(member2.getTasks().contains(newTask));

        //delegate task to another member
        for (Task t : member1.getTasks().stream().toList()) {
            memberService.delegateTask(t, member2);
        }

        System.out.println(member1.getTasks().size());
        System.out.println(member2.getTasks().size());

        //remove member1
        memberService.removeMember(member1);
        List<Long> existMemberIdList = StreamSupport.stream(memberService.list().spliterator(), false)
            .map(Member::getId)
            .toList()
        ;
        System.out.println(existMemberIdList);

        //get member task statues
        var member3 = memberService.read(3L);
        Map<String, Status> taskStatuesByMember = memberService.getTaskStatuesByMember(member3);
        System.out.println(taskStatuesByMember);

        //change task status
        Optional<Task> taskEntry = member3.getTasks()
            .stream()
            .dropWhile(t -> t.getStatus() == Status.DONE)
            .findFirst()
        ;

        if (taskEntry.isPresent()) {
            var task = taskEntry.get();
            taskService.changeStatus(task, Status.DONE);
            System.out.println(task.getStatus());
        }

        //filter task list
        List<Task> taskListByStatus = taskService.getTasksByStatus(Status.CREATED);
        System.out.println(taskListByStatus.size());

        List<Task> taskListByPriority = taskService.getTasksByPriority(Priority.HIGH);
        System.out.println(taskListByPriority.size());

        List<Task> taskListBeforeDateDeadline = taskService.getTasksBeforeDateDeadline(datePlusSevenDays);
        System.out.println(taskListBeforeDateDeadline.size());
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