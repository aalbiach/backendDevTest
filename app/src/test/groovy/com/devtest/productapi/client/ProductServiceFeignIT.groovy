package com.devtest.productapi.client

import com.devtest.productapi.client.payload.response.ProductDto
import com.devtest.productapi.service.client.ProductServiceFeignService
import com.devtest.productapi.util.SimuladoSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceFeignIT extends SimuladoSpecification {

    @Autowired
    private ProductServiceFeignService client

    def "should return a similar product ids when product exists"() {
        given:
        def productId = "1"

        when:
        def result = client.retrieveSimilarProductIds(productId)

        then:
        with(result) {
            it instanceof List<String>
            size() > 0
            each {
                !isEmpty()
            }
        }
    }

    def "should return empty list when similar products returns 404"() {
        given:
        def productId = "10"

        when:
        def result = client.retrieveSimilarProductIds(productId)

        then:
        result == []
    }

    def "should return a product when retrieve product"(String productId) {
        when:
        def result = client.retrieveProduct(productId)

        then:
        with(result) {
            it instanceof ProductDto
            id == productId
            !name.empty
            !price.naN
            price > 0
            availability != null
        }

        where:
        productId << ["1", "2", "3", "4"]
    }

    def "should return null when retrieve product returns 404"() {
        given:
        def productId = "5"

        when:
        def result = client.retrieveProduct(productId)

        then:
        result == null
    }

    def "should return null when retrieve product returns 500"() {
        given:
        def productId = "6"

        when:
        def result = client.retrieveProduct(productId)

        then:
        result == null
    }

    def "should return a product when call is slow"() {
        given:
        def productId = "100"

        when:
        def result = client.retrieveProduct(productId)

        then:
        with(result) {
            it instanceof ProductDto
            id == productId
            !name.empty
            !price.naN
            price > 0
            availability != null
        }
    }

    def "should fallback very slow calls"(String productId) {
        when:
        def result = client.retrieveProduct(productId)

        then:
        result == null

        where:
        productId << ["1000", "10000"]
    }
}
