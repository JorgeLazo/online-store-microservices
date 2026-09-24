package cl.jorgelazo.store.product.repository;

import java.util.List;
import cl.jorgelazo.store.product.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.jorgelazo.store.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);

}
