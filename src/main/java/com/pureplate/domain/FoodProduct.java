package com.pureplate.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "food_products")
@Data
@ToString
public class FoodProduct extends GenericEntity {

  @NotBlank(message = "Name is mandatory")
  private String name;
  @NotBlank(message = "Description is mandatory")
  private String description;
}
