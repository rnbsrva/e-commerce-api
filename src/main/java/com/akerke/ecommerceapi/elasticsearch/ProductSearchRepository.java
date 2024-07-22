package com.akerke.ecommerceapi.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<ProductSearch, String> {

    List<ProductSearch> findByNameOrDescription(String name, String description);

}
