package com.pureplate.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum IngredientType {
  COSMETIC("Cosmetic"),
  INDUSTRIALIZED("Industrialized"),
  MINIMALLY("Minimally Processed"),
  NATURAL("Natural");

  private final String value;

}
