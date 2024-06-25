package com.springboot.blog.repository;

import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//extending JpasRepository, we get all CRUD methods
    Page<Post> findByCategoryId(Long categoryId, PageRequest pageable);
}
