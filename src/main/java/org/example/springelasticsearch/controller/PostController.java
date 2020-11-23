package org.example.springelasticsearch.controller;

import lombok.RequiredArgsConstructor;
import org.example.springelasticsearch.entity.Post;
import org.example.springelasticsearch.entity.PostSearchData;
import org.example.springelasticsearch.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {
    final private PostService postService;

    @GetMapping("/posts")
    public List<Post> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/posts/search")
    public List<PostSearchData> getPosts(@RequestParam String keyword) {
        return postService.searchPosts(keyword);
    }

    @PostMapping("/posts")
    public Post createPost(
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content) {
        return postService.createPost(title, content, userId);
    }
}
