package com.task_management_system.Repository.JPA;

import com.task_management_system.Entity.Member;
import com.task_management_system.Repository.AsMemberRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
@ConditionalOnProperty(name = "app.use_source", havingValue = "jpa")
public interface MemberRepository extends CrudRepository<Member, UUID>, AsMemberRepository {
    @Override
    @Query("SELECT COUNT(m.id) > 0 FROM Member m WHERE LOWER(m.name) = :name")
    boolean isExistByName(@Param("name") String name);

    @Override
    @Query("SELECT COUNT(m.id) > 0 FROM Member m WHERE LOWER(m.name) = :name AND m.id != :existMemberId")
    boolean isExistByName(@Param("name") String name, @Param("existMemberId") UUID id);
}