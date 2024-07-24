package com.akerke.ecommerceapi.elasticsearch;

import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductSearchServiceImpl implements ProductSearchService {

    private final ElasticsearchOperations elasticsearchOperations;

    @Override
    public List<ProductSearch> search(String query) {
        Criteria criteria = Criteria.where("name").contains(query)
                .or(new Criteria("description").contains(query));

        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<ProductSearch> searchHits = elasticsearchOperations.search(searchQuery, ProductSearch.class);

        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());

    }

}
