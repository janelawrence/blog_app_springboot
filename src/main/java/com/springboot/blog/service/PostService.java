package com.springboot.blog.service;

import com.springboot.blog.payload.PostDto;
import com.springboot.blog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto getPostById(long id);

    PostResponse getPostByCategory(int pageNo, int pageSize, String sortBy, String sortDir, long categoryId);

    PostDto updatePostById(PostDto postDto, long id);

    void deletePost(long id);


}
