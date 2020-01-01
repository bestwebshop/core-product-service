package tech.bestwebshop.api.productservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class ProductDTO {

    @NotEmpty
    @NonNull
    private String name;
    @Positive
    @NonNull
    private double price;
    @Positive
    @NonNull
    private int categoryID;
    @NotEmpty
    @NonNull
    private String details;
}
