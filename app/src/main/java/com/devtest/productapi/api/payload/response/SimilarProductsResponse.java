package com.devtest.productapi.api.payload.response;

import com.devtest.productapi.domain.Product;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Value;

import java.util.Set;

@Value
public class SimilarProductsResponse {

    @JsonValue
    Set<Product> products;

}
