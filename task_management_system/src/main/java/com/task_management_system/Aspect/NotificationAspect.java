package com.task_management_system.Aspect;

import com.task_management_system.Entity.Notification;
import com.task_management_system.Entity.Task;
import com.task_management_system.Enum.Type;
import com.task_management_system.Repository.JPA.NotificationRepository;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import java.util.List;

@Aspect
@Component
@ConditionalOnProperty(name = "app.use_source", havingValue = "jpa")
public class NotificationAspect {
    private final NotificationRepository notificationRepository;

    public NotificationAspect(
        NotificationRepository notificationRepository
    ) {
        this.notificationRepository = notificationRepository;
    }

    @AfterReturning(
        pointcut = "execution(* com.task_management_system.Service.TaskService.create(..))",
        returning = "task"
    )
    public void afterTaskCreated(Task task) {
        if (null == task.getMember()) {
            return;
        }

        var notification = new Notification();
        notification.setMember(task.getMember());
        notification.setType(Type.TASK_CREATED);
        notification.setMessage(String.format("Task '%s' has been created.", task.getName()));

        this.notificationRepository.save(notification);
    }

    @AfterReturning(
        pointcut = "execution(* com.task_management_system.Service.TaskService.delegate(..))",
        returning = "task"
    )
    public void afterTaskDelegated(Task task) {
        var notification = new Notification();
        notification.setMember(task.getMember());
        notification.setType(Type.TASK_DELEGATE);
        notification.setMessage(String.format("Task '%s' has been delegated.", task.getName()));

        this.notificationRepository.save(notification);
    }

    @AfterReturning(
        pointcut = "execution(* com.task_management_system.Controller.MemberController.getNotifications(..))",
        returning = "notifications"
    )
    public void afterNotificationsReading(List<Notification> notifications) {
        for (Notification notification : notifications) {
            notification.setRead(true);
            this.notificationRepository.save(notification);
        }
    }
}