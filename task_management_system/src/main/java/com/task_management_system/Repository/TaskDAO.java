package com.task_management_system.Repository;

import com.task_management_system.Entity.Task;
import com.task_management_system.Enum.Priority;
import com.task_management_system.Enum.Status;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.*;

@Repository
public class TaskDAO extends AbstractRepository implements DAO<Task> {
    @Override
    public Optional<Task> find(UUID id) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM task WHERE id = ?")) {
            statement.setObject(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return Optional.of(this.build(result));
            }

            return Optional.empty();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Iterable<Task> findAll() {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM task")) {
            ResultSet result = statement.executeQuery();
            List<Task> tasks = new ArrayList<>();

            while (result.next()) {
                tasks.add(this.build(result));
            }

            return tasks;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void add(Task task) {
        try (
            PreparedStatement statement = this.dataSource.getConnection().prepareStatement(
                "INSERT INTO task (id_member, name, description, status, priority, date_deadline_at) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            )
        ) {
            statement.setObject(1, null != task.getMember() ? task.getMember().getId() : null);
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setObject(4, null != task.getStatus() ? task.getStatus().getName() : null);
            statement.setObject(5, null != task.getPriority() ? task.getPriority().getName() : null);
            statement.setDate(6, this.getSqlData(task.getDateDeadlineAt()));
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) {
                task.setId(result.getObject("id", UUID.class));
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void remove(Task task) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("DELETE FROM task WHERE id = ?")) {
            statement.setObject(1, task.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean isExistByName(String name) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT COUNT(id) > 0 AS count FROM task WHERE LOWER(name) = ?")) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getBoolean("count");
            }

            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean isExistByName(String name, UUID existTaskId) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT COUNT(id) > 0 AS count FROM task WHERE LOWER(name) = ? AND id != ?")) {
            statement.setString(1, name);
            statement.setObject(2, existTaskId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getBoolean("count");
            }

            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Task build(ResultSet result) throws SQLException {
        return Task.builder()
            .id(result.getObject("id", UUID.class))
            .name(result.getString("name"))
            .description(result.getString("description"))
            .status(Status.fromString(result.getString("status")))
            .priority(Priority.fromString(result.getString("priority")))
            .dateDeadlineAt(this.getSimpleData(result.getDate("date_deadline_at")))
            .build()
        ;
    }
}