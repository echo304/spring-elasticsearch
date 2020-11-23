package org.example.springelasticsearch.service;

import lombok.RequiredArgsConstructor;
import org.example.springelasticsearch.entity.Post;
import org.example.springelasticsearch.entity.PostSearchData;
import org.example.springelasticsearch.repository.PostElasticsearchRepository;
import org.example.springelasticsearch.repository.PostRepository;
import org.example.springelasticsearch.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    final private PostRepository postRepository;
    final private UserRepository userRepository;
    final private PostElasticsearchRepository postElasticsearchRepository;

    @Transactional
    public Post createPost(String title, String content, Long userId) {
        Post post = new Post();
        post.setAuthor(userRepository.getOne(userId));
        post.setTitle(title);
        post.setContent(content);

        postRepository.save(post);
        return post;
    }

    @Transactional(readOnly = true)
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    public List<PostSearchData> searchPosts(String keyword) {
        return postElasticsearchRepository.findByContent(keyword);
    }
}
