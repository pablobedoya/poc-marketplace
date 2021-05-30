package poc.marketplace.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import poc.marketplace.dto.ProductRequestDTO;
import poc.marketplace.dto.ProductResponseDTO;
import poc.marketplace.exception.ResourceNotFoundException;
import poc.marketplace.exception.UnprocessableEntityException;
import poc.marketplace.model.Category;
import poc.marketplace.model.Product;
import poc.marketplace.repository.CategoryRepository;
import poc.marketplace.repository.ProductRepository;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductResponseDTO> getProducts(String name, BigDecimal price, Pageable pageable) {
        return productRepository.getProducts(name, price, pageable);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        return ProductResponseDTO.builder().
                name(product.getName()).
                description(product.getDescription()).
                price(product.getPrice()).
                categoryId(product.getCategory().getId()).
                build();
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
    	// TODO: tratar não existência da categoria do produto
		Category category = categoryRepository.findById(productRequestDTO.getCategoryId())
				.orElseThrow(UnprocessableEntityException::new);

        Product product = productRequestDTO.toEntity(category);

        productRepository.save(product);

        return ProductResponseDTO.builder().
                name(product.getName()).
                description(product.getDescription()).
                price(product.getPrice()).
                categoryId(category.getId()).
                build();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

}
