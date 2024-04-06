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
    public Long nextId() {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT MAX(id) AS max FROM task")) {
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                var existLastId = result.getLong("max");

                return ++existLastId;
            }

            return 1L;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Optional<Task> find(Long id) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM task WHERE id = ?")) {
            statement.setLong(1, id);
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
                "INSERT INTO task (id, id_member, name, description, status, priority, date_deadline_at) VALUES (?, ?, ?, ?, ?, ?, ?)"
            )
        ) {
            statement.setLong(1, task.getId());
            statement.setObject(2, null != task.getMember() ? task.getMember().getId() : null);
            statement.setString(3, task.getName());
            statement.setString(4, task.getDescription());
            statement.setObject(5, null != task.getStatus() ? task.getStatus().getName() : null);
            statement.setObject(6, null != task.getPriority() ? task.getPriority().getName() : null);
            statement.setDate(7, this.getSqlData(task.getDateDeadlineAt()));
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void remove(Task task) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("DELETE FROM task WHERE id = ?")) {
            statement.setLong(1, task.getId());
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

    public boolean isExistByName(String name, Long existTaskId) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT COUNT(id) > 0 AS count FROM task WHERE LOWER(name) = ? AND id != ?")) {
            statement.setString(1, name);
            statement.setLong(2, existTaskId);
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
            .id(result.getLong("id"))
            .name(result.getString("name"))
            .description(result.getString("description"))
            .status(Status.fromString(result.getString("status")))
            .priority(Priority.fromString(result.getString("priority")))
            .dateDeadlineAt(this.getSimpleData(result.getDate("date_deadline_at")))
            .build()
        ;
    }
}