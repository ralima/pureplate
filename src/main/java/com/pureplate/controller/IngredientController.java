package com.pureplate.controller;

import com.pureplate.domain.FoodProduct;
import com.pureplate.domain.Ingredient;
import com.pureplate.exception.FoodProductIdMismatchException;
import com.pureplate.repository.FoodProductRepository;
import com.pureplate.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientController {

  @Autowired
  private IngredientRepository ingredientRepository;

  @GetMapping
  public Iterable findAll() {
    return ingredientRepository.findAll();
  }

  @GetMapping("/name/{name}")
  public List findByName(@PathVariable String name) {
    return ingredientRepository.findByName(name);
  }

  @GetMapping("/{id}")
  public Ingredient findOne(@PathVariable UUID id) {
    return ingredientRepository.findById(id)
                         .orElseThrow();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Ingredient create(@RequestBody Ingredient ingredient) {
    return ingredientRepository.save(ingredient);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    ingredientRepository.findById(id)
                  .orElseThrow();
    ingredientRepository.deleteById(id);
  }

  @PutMapping("/{id}")
  public Ingredient updateFoodProduct(@RequestBody Ingredient ingredient,
                                       @PathVariable UUID id) {
    if (ingredient.getPublicId() != id) {
      throw new FoodProductIdMismatchException();
    }
    ingredientRepository.findById(id)
                  .orElseThrow();
    return ingredientRepository.save(ingredient);
  }
}
