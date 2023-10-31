package com.pureplate.domain;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "food_products")
@Data
@ToString
public class FoodProduct extends GenericEntity {

  private String name;
  private String description;

  @ManyToMany
  @JoinTable(name = "food_products_ingredients",
    joinColumns = @JoinColumn(name = "food_product_id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private Set<Ingredient> Ingredients;
}
