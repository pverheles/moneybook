package com.moneybook.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "app_user")
public class User extends BaseEntity {

  @Column(unique = true)
  private String email;

  private String name;

  private String password;

  private Boolean deleted;

  @Column(name = "last_order_id")
  private String lastOrderId;

  @Temporal(TemporalType.DATE)
  @Column(name = "last_order_date")
  private Date lastOrderDate;

  @Temporal(TemporalType.DATE)
  @Column(name = "last_payment_date")
  private Date lastPaymentDate;

  @Column(name = "unlimited_blocked")
  private Boolean unlimitedBlocked;

  @Column(name = "last_payment_amount")
  private Integer lastPaymentAmount;

  @Column(name = "timezone_offset")
  private Short timeZoneOffset;

}