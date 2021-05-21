package poc.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import poc.marketplace.dto.ProductRequestDTO;
import poc.marketplace.dto.ProductResponseDTO;
import poc.marketplace.exception.ResourceNotFoundException;
import poc.marketplace.model.Category;
import poc.marketplace.model.Product;
import poc.marketplace.repository.CategoryRepository;
import poc.marketplace.repository.ProductRepository;
import poc.marketplace.service.factory.ProductFactory;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductFactory productFactory;

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
                categoryName(product.getCategory().getName()).
                build();
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
    	// TODO: tratar não existência da categoria do produto
        Category category = getCategoryById(productRequestDTO.getCategoryId());

        Product product = productFactory.createProductToRegister(productRequestDTO, category);

        productRepository.save(product);

        return ProductResponseDTO.builder().
                name(product.getName()).
                description(product.getDescription()).
                price(product.getPrice()).
                categoryName(category.getName()).
                build();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

}
