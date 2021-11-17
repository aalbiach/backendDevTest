package com.devtest.productapi.client.payload.response;

import com.devtest.productapi.domain.Product;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@Builder(toBuilder = true)
public class ProductDto {
    @NonNull
    @Length(min = 1)
    String id;

    @NonNull
    @Length(min = 1)
    String name;

    @NonNull
    Float price;

    @NonNull
    Boolean availability;

    public Product toProduct() {
        return new Product(id, name, price, availability);
    }
}
