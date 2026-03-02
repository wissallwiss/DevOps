package com.campconnect.repository;

import com.campconnect.enums.ERole;
import com.campconnect.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(ERole name);
}
