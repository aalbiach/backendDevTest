package com.devtest.productapi.service.impl

import com.devtest.productapi.client.ProductServiceClient
import com.devtest.productapi.domain.Product
import com.devtest.productapi.util.BaseSpecification

import java.util.stream.Collectors

class SimilarProductsServiceImplTest extends BaseSpecification {

    private client = Mock(ProductServiceClient)
    private service = new SimilarProductsServiceImpl(client)

    def "should return a similar products for the productId"() {
        given:
        def productId = randomProductId()
        def similarProductsIds = randomProductsIds()

        when:
        def result = service.retrieveSimilarProducts(productId)

        then:
        with(result) {
            it instanceof Set<Product>
            size() == similarProductsIds.size()
        }

        interaction {
            1 * client.retrieveSimilarProductIds(productId) >> similarProductsIds
            similarProductsIds.size() * client.retrieveProduct(_ as String) >> { String id -> randomProductDto(id) }
        }
    }

    def "should not return a similar products for the productId"() {
        given:
        def productId = randomProductId()
        def similarProductsIds = Collections.<String>emptyList()

        when:
        def result = service.retrieveSimilarProducts(productId)

        then:
        with(result) {
            it instanceof Set<Product>
            size() == 0
        }

        interaction {
            1 * client.retrieveSimilarProductIds(productId) >> similarProductsIds
            0 * client.retrieveProduct(_)
        }
    }

    private List<String> randomProductsIds() {
        return random.objects(String.class, randomInt(1, 10))
                .collect(Collectors.toList())
    }
}
