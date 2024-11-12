package org.example.wishlistelectricboogaloo.Service;

import org.example.wishlistelectricboogaloo.Repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

            public ProductService(ProductRepository productRepository) {
            this.productRepository = productRepository ;
            }
}
