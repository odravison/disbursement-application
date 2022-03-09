package es.sequra.disbursementwebapplication.infrastructure.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shopper")
@SQLDelete(sql = "UPDATE shopper SET deleted = true WHERE id = ?")
@Where(clause = BaseEntity.WHERE_DELETED_CLAUSE)
@SequenceGenerator(name="base_entity_gen", allocationSize = 1, sequenceName = "shopper_seq")
public class ShopperEntity extends UserEntity {

    @Column(name = "nif")
    private String nif;
}
