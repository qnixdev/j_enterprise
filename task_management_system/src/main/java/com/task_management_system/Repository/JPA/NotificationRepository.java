package com.task_management_system.Repository.JPA;

import com.task_management_system.Entity.Notification;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.use_source", havingValue = "jpa")
public interface NotificationRepository extends CrudRepository<Notification, UUID> {
}