package com.pureplate.controller;

import com.pureplate.domain.FoodProduct;
import com.pureplate.exception.FoodProductIdMismatchException;
import com.pureplate.exception.FoodProductNotFoundException;
import com.pureplate.repository.FoodProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/food-products")
public class FoodProductController {

  @Autowired
  private FoodProductRepository foodProductRepository;

  @GetMapping
  public Iterable findAll() {
    return foodProductRepository.findAll();
  }

  @GetMapping("/title/{name}")
  public List findByTitle(@PathVariable String name) {
    return foodProductRepository.findByName(name);
  }

  @GetMapping("/{id}")
  public FoodProduct findOne(@PathVariable UUID id) {
    return foodProductRepository.findById(id)
                         .orElseThrow();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FoodProduct create(@RequestBody FoodProduct foodProduct) {
    return foodProductRepository.save(foodProduct);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
    foodProductRepository.findById(id)
                  .orElseThrow();
    foodProductRepository.deleteById(id);
  }

  @PutMapping("/{id}")
  public FoodProduct updateBook(@RequestBody FoodProduct foodProduct, @PathVariable UUID id) {
    if (foodProduct.getPublicId() != id) {
      throw new FoodProductIdMismatchException();
    }
    foodProductRepository.findById(id)
                  .orElseThrow();
    return foodProductRepository.save(foodProduct);
  }
}
