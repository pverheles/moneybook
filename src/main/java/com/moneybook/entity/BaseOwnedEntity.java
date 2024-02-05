package com.moneybook.entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseOwnedEntity extends BaseEntity {

    @ManyToOne
    private User user;
}
