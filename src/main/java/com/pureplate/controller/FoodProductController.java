package com.pureplate.controller;

import com.pureplate.domain.FoodProduct;
import com.pureplate.domain.Ingredient;
import com.pureplate.domain.dto.FoodProductRequest;
import com.pureplate.exception.FoodProductIdMismatchException;
import com.pureplate.exception.FoodProductNotFoundException;
import com.pureplate.repository.FoodProductRepository;
import com.pureplate.repository.IngredientRepository;
import com.pureplate.service.FoodClassificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/food-products")
@AllArgsConstructor
public class FoodProductController {

  private final  FoodProductRepository foodProductRepository;

  private final FoodClassificationService foodClassificationService;

  private final IngredientRepository ingredientRepository;


  @GetMapping
  public Iterable findAll() {
    return foodProductRepository.findAll();
  }

  @GetMapping("/title/{name}")
  public List findByName(@PathVariable String name) {
    return foodProductRepository.findByName(name);
  }

  @GetMapping("/{id}")
  public FoodProduct findOne(@PathVariable UUID id) {
    return foodProductRepository.findById(id)
                         .orElseThrow();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FoodProduct create(@RequestBody FoodProductRequest foodProductRequest) {
    //return foodProductRepository.save(foodProduct);

    FoodProduct foodProduct = new FoodProduct();
    foodProduct.setName(foodProductRequest.getName());
    foodProduct.setDescription(foodProductRequest.getDescription());

    Set<Ingredient> ingredients = foodProductRequest.getIngredients().stream()
                                                    .map(ingredientId -> ingredientRepository.findById(ingredientId).orElseThrow())
                                                    .collect(Collectors.toSet());
    // Set the retrieved persistent Ingredients in the FoodProduct
    foodProduct.getIngredients().addAll(ingredients);
    foodProduct.setIngredients(ingredients);

    FoodProduct savedFoodProduct = foodProductRepository.save(foodProduct);
    return foodProductRepository.save(foodProduct);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    foodProductRepository.findById(id)
                  .orElseThrow();
    foodProductRepository.deleteById(id);
  }

  @PutMapping("/{id}")
  public FoodProduct updateFoodProduct(@RequestBody FoodProduct foodProduct,
                                       @PathVariable UUID id) {
    if (foodProduct.getPublicId() != id) {
      throw new FoodProductIdMismatchException();
    }
    foodProductRepository.findById(id)
                  .orElseThrow();
    return foodProductRepository.save(foodProduct);
  }

  private void classifyFoodTypeAsync(FoodProduct foodProduct) {
    CompletableFuture.runAsync(() -> {
      foodClassificationService.classifyFoodProduct(foodProduct);
    });
  }
}
