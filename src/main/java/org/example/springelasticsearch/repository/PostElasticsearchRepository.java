package org.example.springelasticsearch.repository;

import org.example.springelasticsearch.entity.PostSearchData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PostElasticsearchRepository extends ElasticsearchRepository<PostSearchData, Long> {

    public List<PostSearchData> findByContent(String keyword);
}
