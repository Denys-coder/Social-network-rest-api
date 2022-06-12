package com.example.socialnetworkrestapi.repository;

import com.example.socialnetworkrestapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>
{
    public Optional<UserEntity> findByEmail(String email);
}
