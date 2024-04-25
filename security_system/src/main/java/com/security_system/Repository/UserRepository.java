package com.security_system.Repository;

import com.security_system.Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    User findUserByEmail(String email);
}