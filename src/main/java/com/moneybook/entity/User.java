package com.moneybook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User extends BaseEntity {

  @Column(unique = true)
  private String email;

  private String name;

}