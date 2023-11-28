package com.pureplate.repository;

import com.pureplate.domain.FoodProduct;
import com.pureplate.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface IngredientRepository extends CrudRepository<Ingredient, UUID> {
  List<Ingredient> findByName(String title);

//  Iterable<Ingredient> findAll);
//
//  Ingredient findById(UUID id);
//  Ingredient save(Ingredient ingredient);
//
//  void deleteById(UUID id);
}
