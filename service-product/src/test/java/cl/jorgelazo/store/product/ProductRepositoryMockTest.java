package cl.jorgelazo.store.product;

import java.time.LocalDateTime;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import cl.jorgelazo.store.product.entity.Category;
import cl.jorgelazo.store.product.entity.Product;
import cl.jorgelazo.store.product.repository.ProductRepository;

@DataJpaTest 
public class ProductRepositoryMockTest {

    @Autowired 
    private ProductRepository productRepository;

    @Test 
    public void whenFindByCategoryId_thenReturnProducts() {

        Category category1 = Category.builder().id(1L).build();
        
        Product product1 = Product.builder()
                .name("Product 3")
                .category(category1)
                .description("")
                .stock(10.0)
                .price(100.0)
                .status("ACTIVE")
                .createAt(LocalDateTime.now())
                .build();

        productRepository.save(product1);

        List<Product> products = productRepository.findByCategory(product1.getCategory());
    
        Assertions.assertThat(products.size()).isEqualTo(3);
    
    
    }

}
