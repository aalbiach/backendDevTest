package com.devtest.productapi.service.client;

import com.devtest.productapi.client.payload.response.ProductDto;

import java.util.List;

public interface ProductServiceFeignService {

    List<String> retrieveSimilarProductIds(String productId);

    ProductDto retrieveProduct(String productId);

}
