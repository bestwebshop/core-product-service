package tech.bestwebshop.api.productservice.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
@RequiredArgsConstructor
public class Product implements Serializable {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NonNull
    private int id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "price")
    @NonNull
    private double price;

    @Column(name = "category_id")
    @NonNull
    private int categoryID;

    @Column(name = "details")
    @NonNull
    private String details;
}
