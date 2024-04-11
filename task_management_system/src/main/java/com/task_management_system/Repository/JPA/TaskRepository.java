package com.task_management_system.Repository.JPA;

import com.task_management_system.Entity.Task;
import com.task_management_system.Repository.AsTaskRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.use_source", havingValue = "jpa")
public interface TaskRepository extends CrudRepository<Task, UUID>, AsTaskRepository {
    @Override
    @Query("SELECT COUNT(t.id) > 0 FROM Task t WHERE LOWER(t.name) = :name")
    boolean isExistByName(@Param("name") String name);

    @Override
    @Query("SELECT COUNT(t.id) > 0 FROM Task t WHERE LOWER(t.name) = :name AND t.id != :existTaskId")
    boolean isExistByName(@Param("name") String name, @Param("existTaskId") UUID id);
}