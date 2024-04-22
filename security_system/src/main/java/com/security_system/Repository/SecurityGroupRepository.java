package com.security_system.Repository;

import com.security_system.Entity.SecurityGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SecurityGroupRepository extends CrudRepository<SecurityGroup, UUID> {
    SecurityGroup findSecurityGroupByType(SecurityGroup.Type type);
}