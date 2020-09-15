package poc.marketplace.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import poc.marketplace.dto.ProductResponseDTO;
import poc.marketplace.model.Product;

import java.math.BigDecimal;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select new poc.marketplace.dto.ProductResponseDTO(p.name, p.description, p.price, c.name) from Product p" +
            " join p.category c" +
            " where (:name is null or p.name like %:name%)" +
            " and (:price is null or p.price = :price)")
    Page<ProductResponseDTO> getProducts(@Param("name") String name,
                                         @Param("price") BigDecimal price,
                                         Pageable pageable);

}
