package com.moneyrama.entity;

import com.moneyrama.constants.DBConstants;
import com.moneyrama.constants.EntityStates;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@MappedSuperclass
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Version
	private Long version;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_date")
	private Date createDate;

	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modify_date")
	private Date modifyDate;

	@CreatedBy
	@Column(name = "created_by")
	private String createdBy;

	@LastModifiedBy
	@Column(name = "modified_by")
	private String modifiedBy;

	@ManyToOne
	private User user;

	@Column(length = DBConstants.NAME_LENGTH)
	private String name;

	@Column(length = DBConstants.COMMENTS_LENGTH)
	private String comment;

	private Character state;

	@Transient
	public boolean isActive() {
		return EntityStates.ACTIVE.getCode().equals(getState());
	}
	
	@Override
	public String toString() {
		return name;
	}	

}
