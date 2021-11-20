package com.devtest.productapi.client;

import com.devtest.productapi.client.payload.response.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        value = "product-service",
        url = "${client.product-service.url}")
public interface ProductServiceFeign {

    @GetMapping(
            value = "/product/{productId}/similarids",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    List<String> retrieveSimilarProductIds(@PathVariable String productId);

    @GetMapping(
            value = "/product/{productId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductDto retrieveProduct(@PathVariable String productId);

}
