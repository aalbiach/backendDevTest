package com.devtest.productapi.api.controller;

import com.devtest.productapi.api.payload.response.SimilarProductsResponse;
import com.devtest.productapi.service.SimilarProductsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class SimilarProductsController {

    private final SimilarProductsService similarProductsService;

    @GetMapping("/product/{productId}/similar")
    public ResponseEntity<SimilarProductsResponse> retrieveSimilarProducts(@PathVariable String productId) {
        log.info("Retrieving similar products for ProductId='{}'", productId);
        return ResponseEntity.ok(new SimilarProductsResponse(similarProductsService.retrieveSimilarProducts(productId)));
    }

}
