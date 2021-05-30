package poc.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import poc.marketplace.model.Category;
import poc.marketplace.model.Product;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {

    private String name;

    private String description;

    private BigDecimal price;

    @JsonProperty("category_id")
    private Long categoryId;

    public Product toEntity(Category category) {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .build();
    }

}
