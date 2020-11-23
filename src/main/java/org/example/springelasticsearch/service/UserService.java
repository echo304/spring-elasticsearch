package org.example.springelasticsearch.service;

import lombok.RequiredArgsConstructor;
import org.example.springelasticsearch.entity.User;
import org.example.springelasticsearch.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    final private UserRepository userRepository;

    @Transactional
    public User createUser(String name) {
        User user = new User();
        user.setName(name);

        userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
