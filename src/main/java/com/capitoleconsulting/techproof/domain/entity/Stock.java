package com.capitoleconsulting.techproof.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @JoinColumn(name = "size_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY)
    private Size size;

    @Column(name = "quantity")
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return Objects.equals(size, stock.size)
                && Objects.equals(quantity, stock.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, quantity);
    }

}
