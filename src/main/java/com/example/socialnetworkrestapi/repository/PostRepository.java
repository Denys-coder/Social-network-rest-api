package com.example.socialnetworkrestapi.repository;

import com.example.socialnetworkrestapi.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer>
{

}
