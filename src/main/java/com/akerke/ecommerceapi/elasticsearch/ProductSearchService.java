package com.akerke.ecommerceapi.elasticsearch;

import java.util.List;

public interface ProductSearchService {

    List<ProductSearch> search(String query);

}
