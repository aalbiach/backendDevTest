package com.devtest.productapi.domain;

import lombok.NonNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
public class Product {
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
}
