package poc.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequestDTO {

    private String name;

    private String description;

    private BigDecimal price;

    @JsonProperty("category_id")
    private Long categoryId;

}
