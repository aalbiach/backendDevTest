package com.devtest.productapi.service;

import com.devtest.productapi.domain.Product;

import java.util.Set;

public interface SimilarProductsService {

    Set<Product> retrieveSimilarProducts(String productId);

}
