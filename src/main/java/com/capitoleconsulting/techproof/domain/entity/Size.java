package com.capitoleconsulting.techproof.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "size")
public class Size implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Column(name = "back_soon")
    private Boolean backSoon;

    @Column(name = "special")
    private Boolean special;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "size")
    private Stock stock;

    public boolean hasStock() {
        return Objects.nonNull(stock) && stock.getQuantity() > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Size size = (Size) o;
        return Objects.equals(id, size.id)
                && Objects.equals(backSoon, size.backSoon)
                && Objects.equals(special, size.special);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, backSoon, special);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("backSoon", backSoon)
                .append("special", special)
                .append("stock", Objects.isNull(stock) ? null: stock.getQuantity())
                .toString();
    }
}
