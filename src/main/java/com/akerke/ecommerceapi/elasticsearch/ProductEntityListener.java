package com.akerke.ecommerceapi.elasticsearch;

import com.akerke.ecommerceapi.model.Product;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductEntityListener {

    @Autowired
    private ProductDocumentRepository productDocumentRepository;

    @PostPersist
    @PostUpdate
    public void onPostSave(Product product) {
        ProductDocument document = new ProductDocument();
        document.setId(product.getId());
        document.setName(product.getName());
        document.setDescription(product.getDescription());
        document.setImage(product.getImage());
        productDocumentRepository.save(document);
    }

    @PostRemove
    public void onPostRemove(Product product) {
        productDocumentRepository.deleteById(product.getId());
    }
}
