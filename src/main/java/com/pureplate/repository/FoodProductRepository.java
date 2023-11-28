package com.pureplate.repository;

import com.pureplate.domain.FoodProduct;
import com.pureplate.domain.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface FoodProductRepository  extends CrudRepository<FoodProduct, UUID> {
  List<FoodProduct> findByName(String name);

//  FoodProduct findById(UUID id);
//  FoodProduct save(Ingredient ingredient);
//
//  void deleteById(UUID id);
}
