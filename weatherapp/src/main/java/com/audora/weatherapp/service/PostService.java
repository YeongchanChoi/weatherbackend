package com.audora.weatherapp.service;

import com.audora.weatherapp.entity.Post;
import com.audora.weatherapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
    public List<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시물을 찾을 수 없습니다."));
    }

}
