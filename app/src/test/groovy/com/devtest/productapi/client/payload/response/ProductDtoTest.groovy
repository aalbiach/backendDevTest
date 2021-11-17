package com.devtest.productapi.client.payload.response

import com.devtest.productapi.util.BaseSpecification

class ProductDtoTest extends BaseSpecification {
    def "should return a Product"() {
        given:
        def productDto = randomProductDto()

        when:
        def product = productDto.toProduct()

        then:
        with(product) {
            id == productDto.getId()
            name == productDto.getName()
            price == productDto.getPrice()
            availability == productDto.getAvailability()
        }
    }
}
