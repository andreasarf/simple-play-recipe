package dto

import play.api.libs.json.{Format, Json, JsonConfiguration, JsonNaming}

case class UpdateRecipeRequest(title: Option[String], 
                               makingTime: Option[String], 
                               serves: Option[String], 
                               ingredients: Option[String], 
                               cost: Option[Int])

object UpdateRecipeRequest {
  // json formatting
  given Format[UpdateRecipeRequest] = {
    given JsonConfiguration = JsonConfiguration(JsonNaming.SnakeCase)
    Json.format[UpdateRecipeRequest]
  }
}
