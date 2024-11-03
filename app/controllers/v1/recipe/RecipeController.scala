package controllers.v1.recipe

import dto.{CreateRecipeRequest, RecipeResponse, UpdateRecipeRequest}
import play.api.libs.json.*
import play.api.mvc.*
import services.RecipeService

import javax.inject.Inject

class RecipeController @Inject()(val controllerComponents: ControllerComponents, val service: RecipeService) extends BaseController {

  def getAll: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    //    val recipes = List(
    //      RecipeResponse(1, "Cream Pasta", "45", "5", "Butter,Onion,Garlic,Shrimp,Avocado,Heavy Cream,Pasta, Salt,Pepper", "2"),
    //      RecipeResponse(2, "Avocado Toast", "3", "1", "Avocado,Toasted Bread,Salt,Pepper", "1"),
    //      RecipeResponse(3, "Strawberry Smoothie", "5", "2", "Strawberry, Banana, Milk", "1")
    //    )
    val recipes = service.findAll()
      .map(recipe => RecipeResponse.fromRecipe(recipe))
    Ok(Json.obj("recipes" -> recipes))
  }

  def get(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
//    val recipe = RecipeResponse(id, "Cream Pasta", "45", "5", "Butter,Onion,Garlic,Shrimp,Avocado,Heavy Cream,Pasta, Salt,Pepper", "2")
    service.find(id).fold {
      NotFound(Json.obj("message" -> "No Recipe found"))
    } { recipe =>
      Ok(Json.obj(
        "message" -> "Recipe details by id",
        "recipe" -> List(RecipeResponse.fromRecipe(recipe))
      ))
    }
  }

  def create: Action[JsValue] = Action(parse.json) { implicit request: Request[JsValue] =>
    val requestBody = request.body.validate[CreateRecipeRequest]
    requestBody.fold(
      errors => {
        Ok(Json.obj(
          "message" -> "Recipe creation failed!",
          "required" -> "title, making_time, serves, ingredients, cost"
        ))
      },
      recipe => {
        Ok(Json.obj(
          "message" -> "Recipe successfully created!",
          "recipe" -> List(service.save(recipe.toRecipe).get)
        ))
      }
    )
  }

  def update(id: Int): Action[JsValue] = Action(parse.json) { implicit request: Request[JsValue] =>
    val requestBody = request.body.validate[UpdateRecipeRequest]
    requestBody.fold(
      errors => {
        BadRequest(Json.obj("message" -> JsError.toJson(errors)))
      },
      recipe => {
        service.update(id, recipe).fold {
          NotFound(Json.obj("message" -> "No Recipe found"))
        } { recipe =>
          Ok(Json.obj(
            "message" -> "Recipe successfully updated!",
            "recipe" -> List(RecipeResponse.fromRecipe(recipe))
          ))
        }
      }
    )
  }

  def delete(id: Int): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
//    val recipe = RecipeResponse(id, "Cream Pasta", "45", "5", "Butter,Onion,Garlic,Shrimp,Avocado,Heavy Cream,Pasta, Salt,Pepper", "2")
    if (service.delete(id)) {
      Ok(Json.obj("message" -> "Recipe successfully deleted!"))
    } else {
      NotFound(Json.obj("message" -> "No Recipe found"))
    }
  }
}
