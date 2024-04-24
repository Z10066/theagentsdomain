package com.highwayns.loginservice.repository;

import com.highwayns.loginservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
