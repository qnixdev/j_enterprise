package com.task_management_system.Repository.DAO;

import com.task_management_system.Entity.Member;
import com.task_management_system.Repository.AsMemberRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
@ConditionalOnProperty(name = "app.use_source", havingValue = "dao")
public class MemberDAO implements AsMemberRepository {
    private final DataSource dataSource;

    public MemberDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Member> findById(UUID id) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("SELECT * FROM member WHERE id = ?")) {
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

    @Override
    public Member save(Member member) {
        try (
            PreparedStatement statement = this.dataSource.getConnection().prepareStatement(
                "INSERT INTO member (name) VALUES (?)",
                Statement.RETURN_GENERATED_KEYS
            )
        ) {
            statement.setString(1, member.getName());
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) {
                member.setId(result.getObject("id", UUID.class));
            }

            return member;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(Member member) {
        try (PreparedStatement statement = this.dataSource.getConnection().prepareStatement("DELETE FROM member WHERE id = ?")) {
            statement.setObject(1, member.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean isExistByName(String name) {
        try (
            PreparedStatement statement = this.dataSource.getConnection().prepareStatement(
                "SELECT COUNT(id) > 0 AS count FROM member WHERE LOWER(name) = ?"
            )
        ) {
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

    @Override
    public boolean isExistByName(String name, UUID existMemberId) {
        try (
            PreparedStatement statement = this.dataSource.getConnection().prepareStatement(
                "SELECT COUNT(id) > 0 AS count FROM member WHERE LOWER(name) = ? AND id != ?"
            )
        ) {
            statement.setString(1, name);
            statement.setObject(2, existMemberId);
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
            .id(result.getObject("id", UUID.class))
            .name(result.getString("name"))
            .tasks(new ArrayList<>())
            .build()
        ;
    }
}