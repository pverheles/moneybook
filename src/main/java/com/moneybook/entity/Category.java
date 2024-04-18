package com.moneybook.entity;

import com.moneybook.constants.DBConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category extends BaseOwnedEntity {

    @Column(length = DBConstants.NAME_LENGTH)
    private String name;

}
