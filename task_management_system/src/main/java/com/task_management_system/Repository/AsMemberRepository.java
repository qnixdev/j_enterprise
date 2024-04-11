package com.task_management_system.Repository;

import com.task_management_system.Entity.Member;
import java.util.Optional;
import java.util.UUID;

public interface AsMemberRepository {
    Optional<Member> findById(UUID id);

    Iterable<Member> findAll();

    Member save(Member member);

    void delete(Member entity);

    boolean isExistByName(String name);

    boolean isExistByName(String name, UUID id);
}