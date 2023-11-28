package com.pureplate.service;

import com.pureplate.domain.FoodProduct;
import com.pureplate.domain.Ingredient;
import com.pureplate.domain.enums.FoodClassification;
import com.pureplate.domain.enums.IngredientType;
import com.pureplate.repository.FoodProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class FoodClassificationService {

  @Autowired
  private FoodProductRepository foodProductRepository;

  public FoodProduct classifyFoodProduct(FoodProduct food) {

    FoodClassification foodClassification = classify(food.getIngredients());

    food.setScore(foodClassification);

    return foodProductRepository.save(food);
  }

  private FoodClassification classify(Set<Ingredient> ingredients){
    long totalIngredients = ingredients.size();

    long numCosmetic = countIngredientsOfType(ingredients, IngredientType.COSMETIC);
    long numIndustrialized = countIngredientsOfType(ingredients, IngredientType.INDUSTRIALIZED);
    long numMinimally = countIngredientsOfType(ingredients, IngredientType.MINIMALLY);
    long numNatural = countIngredientsOfType(ingredients, IngredientType.NATURAL);

    if (percentage(numCosmetic, totalIngredients) >= 25) {
      return FoodClassification.ULTRA_PROCESSED;
    } else if (percentage(numIndustrialized, totalIngredients) >= 25) {
      return FoodClassification.PROCESSED;
    } else if (percentage(numCosmetic, totalIngredients) < 25 && percentage(numIndustrialized, totalIngredients) < 25) {
      return FoodClassification.PROCESSED_CULINARY_INGREDIENTS;
    } else {
      return FoodClassification.UNPROCESSED;
    }
  }

  private long countIngredientsOfType(Set<Ingredient> ingredients, IngredientType type) {
    return ingredients.stream().filter(ingredient -> ingredient.getIngredientType() == type).count();
  }

  private double percentage(long part, long total) {
    return (part * 100.0) / total;
  }
}
