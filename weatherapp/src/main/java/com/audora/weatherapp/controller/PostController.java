package com.audora.weatherapp.controller;

import com.audora.weatherapp.entity.Post;
import com.audora.weatherapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시물 목록 조회
    @GetMapping
    public ResponseEntity<?> getAllPosts(Pageable pageable) {
        List<Post> posts = postService.getAllPosts(pageable);
        return ResponseEntity.ok(posts);
    }

    // 게시물 작성
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        Post savedPost = postService.createPost(post);
        return ResponseEntity.ok(savedPost);
    }

    // 게시물 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }
}
