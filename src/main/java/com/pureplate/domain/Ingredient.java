package com.pureplate.domain;

import com.pureplate.domain.enums.IngredientType;
import com.pureplate.domain.enums.Unit;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "ingredients")
@Data
@ToString
public class Ingredient extends GenericEntity{

  private String name;
  private BigDecimal amount;
  @Enumerated(EnumType.STRING)
  private Unit unit;

  private String description;

  @Enumerated(EnumType.STRING)
  private IngredientType ingredientType;
  @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
  private Set<FoodProduct> foodProducts;

  @Transient
  boolean isUltraProcessed() {
    return this.ingredientType.equals(IngredientType.COSMETIC) ? true : false;
  }

  @Transient
  boolean isIndustrialized() {
    return this.ingredientType.equals(IngredientType.INDUSTRIALIZED) ? true : false;
  }

  @Transient
  boolean isMinimally() {
    return this.ingredientType.equals(IngredientType.MINIMALLY) ? true : false;
  }

  @Transient
  boolean isNatural() {
    return this.ingredientType.equals(IngredientType.NATURAL) ? true : false;
  }

}
