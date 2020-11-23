package org.example.springelasticsearch.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@NoArgsConstructor
@Getter
@Setter
@Document(indexName = "post")
public class PostSearchData {

    @Id
    private Long id;

    private String title;
    private String content;
}
