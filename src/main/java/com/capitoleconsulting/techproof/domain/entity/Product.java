package com.capitoleconsulting.techproof.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "sequence")
    private Integer sequence;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Size> sizeList;

    public boolean hasStock() {
        if (CollectionUtils.isEmpty(sizeList)) {
            return false;
        }
        int nOfSpecialWithStock = 0;
        int nOfNoSpecialWithStock = 0;
        for (Size size : sizeList) {
            if (hasBackSoonAndNotSpecial(size)) {
                return true;
            }
            if (size.hasStock()) {
                if (Boolean.TRUE.equals(size.getSpecial())) {
                    nOfSpecialWithStock++;
                } else {
                    nOfNoSpecialWithStock++;
                }
            }
        }

        return nOfSpecialWithStock > 0 && nOfNoSpecialWithStock > 0;
    }

    private boolean hasBackSoonAndNotSpecial(Size size) {
        return Boolean.TRUE.equals(size.getBackSoon()) && Boolean.FALSE.equals(size.getSpecial());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(id, product.id)
                && Objects.equals(sequence, product.sequence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sequence);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", id)
                .append("sequence", sequence)
                .append("sizeList", sizeList)
                .toString();
    }
}
