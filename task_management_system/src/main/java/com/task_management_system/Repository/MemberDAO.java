package com.task_management_system.Repository;

import com.task_management_system.Entity.Member;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.*;

@Repository
public class MemberDAO extends AbstractRepository implements DAO<Member> {
    @Override
    public Long nextId() {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT MAX(id) AS max FROM member")) {
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
    public Optional<Member> find(Long id) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM member WHERE id = ?")) {
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
    public Iterable<Member> findAll() {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM member")) {
            ResultSet result = statement.executeQuery();
            List<Member> members = new ArrayList<>();

            while (result.next()) {
                members.add(this.build(result));
            }

            return members;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void add(Member member) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("INSERT INTO member (id, name) VALUES (?, ?)")) {
            statement.setLong(1, member.getId());
            statement.setString(2, member.getName());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void remove(Member member) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("DELETE FROM member WHERE id = ?")) {
            statement.setLong(1, member.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean isExistByName(String name) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT COUNT(id) > 0 AS count FROM member WHERE LOWER(name) = ?")) {
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

    public boolean isExistByName(String name, Long existMemberId) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT COUNT(id) > 0 AS count FROM member WHERE LOWER(name) = ? AND id != ?")) {
            statement.setString(1, name);
            statement.setLong(2, existMemberId);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return result.getBoolean("count");
            }

            return false;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Member build(ResultSet result) throws SQLException {
        return Member.builder()
            .id(result.getLong("id"))
            .name(result.getString("name"))
            .tasks(new ArrayList<>())
            .build()
        ;
    }
}