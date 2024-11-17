package com.audora.weatherapp.controller;

import com.audora.weatherapp.entity.Comment;
import com.audora.weatherapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 작성
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.createComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    // 특정 게시물의 댓글 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}
