package com.pureplate.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FoodClassification {
  ULTRA_PROCESSED("Ultra Processed"),
  PROCESSED("Processed"),
  PROCESSED_CULINARY_INGREDIENTS("Processed Culinary Ingredients"),
  UNPROCESSED("Unprocessed");

  private final String value;

}
