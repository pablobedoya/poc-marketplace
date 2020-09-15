package poc.marketplace.repository;

import org.springframework.data.repository.CrudRepository;
import poc.marketplace.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
