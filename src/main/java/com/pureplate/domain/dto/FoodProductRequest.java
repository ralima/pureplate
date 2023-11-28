package com.pureplate.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class FoodProductRequest {

  @NotBlank(message = "Name is mandatory")
  private String name;

  @NotBlank(message = "Description is mandatory")
  private String description;

  private Set<UUID> ingredients;
}
