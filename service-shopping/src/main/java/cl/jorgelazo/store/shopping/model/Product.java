package cl.jorgelazo.store.shopping.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data 
public class Product {

    private Long id;
    private String name;
    private String description;
    private Double stock;
    private Double price;
    private String status;
    private LocalDateTime createAt;
    private Category category;

}
