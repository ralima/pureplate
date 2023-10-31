package com.pureplate.controller;

import com.pureplate.domain.FoodProduct;
import com.pureplate.repository.FoodProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodProductViewController {

  @Autowired
  private FoodProductRepository foodProductRepository;

  @GetMapping("/food-products-list")
  public String listFoodProduct(Model model) {

    model.addAttribute("foodProducts", foodProductRepository.findAll());
    return "food-products-list";
  }
  @GetMapping("/food-products-new")
  public String newFoodProducts() {
    return "food-products-new";
  }

  @PostMapping("/food-product-add")
  public String addUser(@Valid @ModelAttribute("foodProduct") FoodProduct foodProduct,
                        BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "food-products-new";
    }

    foodProductRepository.save(foodProduct);
    return "redirect:/food-products-list";
  }
}
