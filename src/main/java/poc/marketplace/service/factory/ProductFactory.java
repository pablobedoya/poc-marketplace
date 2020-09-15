package poc.marketplace.service.factory;

import org.springframework.stereotype.Component;
import poc.marketplace.dto.ProductRequestDTO;
import poc.marketplace.model.Category;
import poc.marketplace.model.Product;

@Component
public class ProductFactory {

    public Product createProductToRegister(ProductRequestDTO productRequestDTO, Category category) {
        Product product = new Product();

        product.setName(productRequestDTO.getName());
        product.setDescription(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setCategory(category);

        return product;
    }

}
