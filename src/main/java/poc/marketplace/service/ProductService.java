package poc.marketplace.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import poc.marketplace.dto.ProductRequestDTO;
import poc.marketplace.dto.ProductResponseDTO;
import poc.marketplace.model.Product;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductService {

    Page<ProductResponseDTO> getProducts(String name, BigDecimal price, Pageable pageable);

    ProductResponseDTO getProductById(Long id);

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);

    void deleteProductById(Long id);

}
