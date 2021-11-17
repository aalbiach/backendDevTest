package com.devtest.productapi.controller

import com.devtest.productapi.api.controller.SimilarProductsController
import com.devtest.productapi.api.payload.response.SimilarProductsResponse
import com.devtest.productapi.domain.Product
import com.devtest.productapi.service.SimilarProductsService
import com.devtest.productapi.util.BaseSpecification
import org.jeasy.random.EasyRandom
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.util.stream.Collectors

class SimilarProductsControllerTest extends BaseSpecification {

    private service = Mock(SimilarProductsService)
    private controller = new SimilarProductsController(service)

    def "should return a similar products for the productId"() {
        given:
        def productId = randomProductId()
        def products = randomSetOfProducts()

        when:
        def result = controller.retrieveSimilarProducts(productId)

        then:
        with(result) {
            it instanceof ResponseEntity<SimilarProductsResponse>
            statusCode == HttpStatus.OK
            with(body) {
                it instanceof SimilarProductsResponse
                it.products instanceof Set<Product>
                it.products.size() == products.size()
            }
        }
        interaction {
            1 * service.retrieveSimilarProducts(productId) >> products
        }
    }

    def "should not return a similar products for the productId"() {
        given:
        def productId = randomProductId()
        def products = Collections.<Product>emptySet()

        when:
        def result = controller.retrieveSimilarProducts(productId)

        then:
        with(result) {
            it instanceof ResponseEntity<SimilarProductsResponse>
            statusCode == HttpStatus.OK
            with(body) {
                it instanceof SimilarProductsResponse
                it.products instanceof Set<Product>
                it.products.size() == 0
            }
        }
        interaction {
            1 * service.retrieveSimilarProducts(productId) >> products
        }
    }

    private Set<Product> randomSetOfProducts() {
        return random.objects(Product.class, randomInt(1, 10))
                .collect(Collectors.toSet())
    }
}
