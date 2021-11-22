package com.devtest.productapi.controller


import com.devtest.productapi.util.SimuladoSpecification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static org.hamcrest.Matchers.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SimilarProductsControllerIT extends SimuladoSpecification {

    @Autowired
    private MockMvc mockMvc

    def "should return a similar products for the productId"(String productId, int expectedSize, String id, String name, String price, String availability) {
        expect:
        mockMvc.perform(get("/product/{productId}/similar", productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath('$').isArray())
                .andExpect(jsonPath('$', hasSize(expectedSize)))
                .andExpect(jsonPath('$[0].*', hasSize(4)))
                .andExpect(jsonPath('$[0].id').value(id))
                .andExpect(jsonPath('$[0].name').value(name))
                .andExpect(jsonPath('$[0].price').value(price))
                .andExpect(jsonPath('$[0].availability').value(availability))

        where:
        productId | expectedSize | id | name | price | availability
        "1" | 3 | "4" | "Boots" | 39.99 | true
        "2" | 2 | "3" | "Blazer" | 29.99 | false
        "3" | 1 | "100" | "Trousers" | 49.99 | false
    }
}
