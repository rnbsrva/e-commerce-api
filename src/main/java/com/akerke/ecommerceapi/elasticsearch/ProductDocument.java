package com.akerke.ecommerceapi.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Getter
@Setter
@Document(indexName = "products")
public class ProductDocument {

    @Id
    private Long id;
    private String name;
    private String description;
    private String image;
}
