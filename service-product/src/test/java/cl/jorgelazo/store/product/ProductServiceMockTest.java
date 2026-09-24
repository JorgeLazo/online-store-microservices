package cl.jorgelazo.store.product;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.jorgelazo.store.product.entity.Category;
import cl.jorgelazo.store.product.entity.Product;
import cl.jorgelazo.store.product.repository.ProductRepository;
import cl.jorgelazo.store.product.service.ProductServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductServiceMockTest {

    @Mock 
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach 
    public void setup() {
        
        product = Product.builder()
                .id(1L)
                .name("Product 1")
                .description("Description 1")
                .category(Category.builder().id(1L).build())
                .price(12.5)
                .stock(100.0)
                .build();

    }

    @Test 
    public void whenValidGetId_ThenReturnProduct() {

        Mockito.when(productRepository.findById(1L))
        .thenReturn(Optional.of(product));    

        Product found = productService.getProduct(1L);
        assertThat(found.getName()).isEqualTo("Product 1");

    }

    @Test
    public void whenValidUpdateStock_ThenReturnNewStock() {

        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);


        Product newStock = productService.updateStock(1L, 8.0);
        assertThat(newStock.getStock()).isEqualTo(108.0);
    }

}
