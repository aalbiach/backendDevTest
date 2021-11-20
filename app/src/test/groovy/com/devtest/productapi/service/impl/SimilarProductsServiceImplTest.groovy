package com.devtest.productapi.service.impl

import com.devtest.productapi.domain.Product
import com.devtest.productapi.service.client.ProductServiceFeignService
import com.devtest.productapi.util.BaseSpecification

import java.util.stream.Collectors

class SimilarProductsServiceImplTest extends BaseSpecification {

    private feignService = Mock(ProductServiceFeignService)
    private service = new SimilarProductsServiceImpl(feignService)

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
            1 * feignService.retrieveSimilarProductIds(productId) >> similarProductsIds
            similarProductsIds.size() * feignService.retrieveProduct(_ as String) >> { String id -> randomProductDto(id) }
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
            1 * feignService.retrieveSimilarProductIds(productId) >> similarProductsIds
            0 * feignService.retrieveProduct(_)
        }
    }

    private List<String> randomProductsIds() {
        return random.objects(String.class, randomInt(1, 10))
                .collect(Collectors.toList())
    }
}
