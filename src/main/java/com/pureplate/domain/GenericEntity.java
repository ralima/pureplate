package com.pureplate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@ToString
@EqualsAndHashCode
public class GenericEntity {
  @Id
  @Column(columnDefinition = "uuid", updatable = false )
  @GeneratedValue
  @Getter
  private UUID publicId;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  @Getter
  private Date createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  @Getter
  private Date updatedAt;
}
