package cl.jorgelazo.store.customer.entity;

import java.io.Serializable;

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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data 
@Entity 
@Table (name = "tbl_customers")
public class Customer implements Serializable {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message = "El numero de documento no puede estar vacío")
    @Size (min = 8, max = 8, message = "El numero de documento debe tener 8 caracteres")
    @Column (name = "number_id", nullable = false, length = 8, unique = true)
    private String numberID;

    @NotEmpty (message = "El nombre no puede estar vacío")
    @Column (name = "first_name", nullable = false, length = 100)
    private String firstName;
    
    @NotEmpty (message = "El apellido no puede estar vacío")
    @Column (name = "last_name", nullable = false, length = 100)
    private String lastName;

    @NotEmpty (message = "El correo electrónico no puede estar vacío")
    @Email (message = "El correo electrónico no es válido")
    @Column (nullable = false, length = 100, unique = true)
    private String email;

    @Column (name = "photo_url", length = 255)
    private String photoUrl;

    @NotNull (message = "La región no puede estar vacía")
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "region_id")
    @JsonIgnoreProperties ({"hibernateLazyInitializer", "handler"})
    private Region region;

    private String state;

}
