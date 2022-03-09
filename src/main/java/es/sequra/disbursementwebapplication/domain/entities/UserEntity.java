package es.sequra.disbursementwebapplication.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class UserEntity extends BaseEntity {

    private String name;
    private String email;

}
