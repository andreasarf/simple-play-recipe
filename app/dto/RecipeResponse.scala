package dto

import models.Recipe
import play.api.libs.json.*

case class RecipeResponse(id: Long, 
                          title: String, 
                          makingTime: String, 
                          serves: String, 
                          ingredients: String, 
                          cost: String)

object RecipeResponse{
  // json formatting
  given Format[RecipeResponse] = {
    given JsonConfiguration = JsonConfiguration(JsonNaming.SnakeCase)
    Json.format[RecipeResponse]
  }

  def fromRecipe(recipe: Recipe): RecipeResponse = {
    RecipeResponse(recipe.id.get, recipe.title, recipe.makingTime, recipe.serves, recipe.ingredients, recipe.cost.toString)
  }
}
