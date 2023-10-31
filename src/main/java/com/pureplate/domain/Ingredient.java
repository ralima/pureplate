package com.pureplate.domain;

import com.pureplate.domain.enums.Unit;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
@ToString
public class Ingredient extends GenericEntity{

  private String name;
  private Double amount;
  private Unit unit;

  private String Description;
  @ManyToMany(mappedBy = "ingredients")
  private Set<FoodProduct> foodProducts;

}
