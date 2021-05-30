package poc.marketplace.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import poc.marketplace.dto.ProductRequestDTO;
import poc.marketplace.dto.ProductResponseDTO;
import poc.marketplace.service.ProductService;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<ProductResponseDTO> getProducts(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "price", required = false) BigDecimal price,
                                     Pageable pageable) {
        return productService.getProducts(name, price, pageable);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO) {
        return productService.createProduct(productRequestDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable ("id") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

}
