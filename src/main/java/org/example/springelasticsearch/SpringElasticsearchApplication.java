package org.example.springelasticsearch;

import org.elasticsearch.client.RestHighLevelClient;
import org.example.springelasticsearch.entity.Post;
import org.example.springelasticsearch.entity.User;
import org.example.springelasticsearch.repository.PostRepository;
import org.example.springelasticsearch.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@EnableSwagger2
@EnableJpaAuditing
@EnableElasticsearchRepositories
@SpringBootApplication
public class SpringElasticsearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringElasticsearchApplication.class, args);
    }

    @Bean
    CommandLineRunner init (UserRepository userRepository, PostRepository postRepository){
        return args -> {
            List<String> names = Arrays.asList("udara", "sampath");
            names.forEach(name -> {
                User user = new User();
                user.setName(name);
                userRepository.save(user);

                Post post = new Post();
                post.setTitle("first");
                post.setContent("first");
                post.setAuthor(user);

                Post post2 = new Post();
                post2.setTitle("second");
                post2.setContent("second");
                post2.setAuthor(user);

                postRepository.saveAll(Arrays.asList(post, post2));
            });
        };
    }

    @Bean
    public RestHighLevelClient client() {
        ClientConfiguration clientConfiguration
                = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    @Bean
    public ElasticsearchOperations elasticsearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}
