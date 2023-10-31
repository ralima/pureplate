package com.pureplate.repository;

import com.pureplate.domain.FoodProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface FoodProductRepository  extends CrudRepository<FoodProduct, UUID> {
  List<FoodProduct> findByName(String name);
}
