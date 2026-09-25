package cl.jorgelazo.store.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.jorgelazo.store.shopping.model.Product;

@FeignClient(name = "service-product", path = "/api/v1/products") 
public interface ProductClient {

    @GetMapping ("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id);

    @PutMapping("/{id}/stock")
    public ResponseEntity<Product> updateStockProduct(@PathVariable Long id, @RequestParam(name = "quantity", required = true) Double quantity);
}
