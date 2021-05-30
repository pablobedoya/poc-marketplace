package poc.marketplace.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private String name;

    private String description;

    private BigDecimal price;

    @JsonProperty("category_id")
    private Long categoryId;

}
