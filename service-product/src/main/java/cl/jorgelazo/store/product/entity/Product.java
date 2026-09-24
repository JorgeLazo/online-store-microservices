package cl.jorgelazo.store.product.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "tbl_products")
@Data 
@AllArgsConstructor @NoArgsConstructor @Builder 
public class Product {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The field name must not be empty")
    private String name;
    private String description;

    @Positive (message = "The field stock must be greater than zero")
    private Double stock;
    @Positive (message = "The field price must be greater than zero")
    private Double price;
    private String status;

    @Column (name = "create_at")
    private LocalDateTime createAt;

    @NotNull (message = "The field category must not be null")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "category_id")
    @JsonIgnoreProperties ({"hibernateLazyInitializer", "handler"})
    private Category category;

}
