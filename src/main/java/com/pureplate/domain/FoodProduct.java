package com.pureplate.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "food_products")
@Data
@ToString
public class FoodProduct extends GenericEntity {

  private String name;
  private String description;
}
