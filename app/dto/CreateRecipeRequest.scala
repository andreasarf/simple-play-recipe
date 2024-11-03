package dto

import models.Recipe
import play.api.libs.json.{Format, Json, JsonConfiguration, JsonNaming}

case class CreateRecipeRequest(title: String, 
                               makingTime: String, 
                               serves: String, 
                               ingredients: String, 
                               cost: Int) {
  def toRecipe: Recipe = {
    Recipe(Option.empty, title, makingTime, serves, ingredients, cost, Option.empty, Option.empty)
  }
}

object CreateRecipeRequest {
  // json formatting
  given Format[CreateRecipeRequest] = {
    given JsonConfiguration = JsonConfiguration(JsonNaming.SnakeCase)
    Json.format[CreateRecipeRequest]
  }
}
