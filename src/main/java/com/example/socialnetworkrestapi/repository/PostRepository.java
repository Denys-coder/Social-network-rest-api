package com.example.socialnetworkrestapi.repository;

import com.example.socialnetworkrestapi.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long>
{
    public List<PostEntity> getByUserId(int userId);
    public Optional<PostEntity> getById(long id);
}
