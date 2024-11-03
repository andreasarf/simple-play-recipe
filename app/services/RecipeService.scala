package services

import dto.UpdateRecipeRequest
import models.{Recipe, RecipeRepository}

import javax.inject.Inject

class RecipeService @Inject(repository: RecipeRepository) {

  def save(recipe: Recipe): Option[Recipe] = {
    repository.insert(recipe)
      .map(id => repository.findById(id).orNull)
  }

  def find(id: Long): Option[Recipe] = {
    repository.findById(id)
  }

  def findAll(): Seq[Recipe] = {
    repository.findAll()
  }

  def update(id: Long, update: UpdateRecipeRequest): Option[Recipe] = {
    repository.findById(id)
      .map(recipe => {
        val updatedRecipe = recipe.copy(
          title = update.title.fold(recipe.title)(title => title),
          makingTime = update.makingTime.fold(recipe.makingTime)(makingTime => makingTime),
          serves = update.serves.fold(recipe.serves)(serves => serves),
          ingredients = update.ingredients.fold(recipe.ingredients)(ingredients => ingredients),
          cost = update.cost.fold(recipe.cost)(cost => cost)
        )
        repository.update(id, updatedRecipe)
      }).filter(u => u == 1)
      .map(_ => repository.findById(id).orNull)
  }

  def delete(id: Long): Boolean = {
    repository.delete(id) == 1
  }

}
