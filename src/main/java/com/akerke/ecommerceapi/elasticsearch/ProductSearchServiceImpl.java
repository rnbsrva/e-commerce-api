package com.akerke.ecommerceapi.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<ProductSearch> search(String query) {
        return elasticsearchOperations.search(
                        new NativeSearchQueryBuilder()
                                .withQuery(QueryBuilders.multiMatchQuery(query, "name", "description"))
                                .build(), ProductSearch.class)
                .stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }

}
