package models

import anorm.{Macro, ToParameterList}
import play.api.libs.json.*

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

case class Recipe(id: Option[Long] = None,
                  title: String,
                  makingTime: String,
                  serves: String,
                  ingredients: String,
                  cost: Int,
                  createdAt: Option[LocalDateTime],
                  updatedAt: Option[LocalDateTime])

object Recipe {
  given ToParameterList[Recipe] = Macro.toParameters[Recipe]

  // json formatting
  given Writes[LocalDateTime] = Writes.temporalWrites[LocalDateTime, DateTimeFormatter](
    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
  )
  given Format[Recipe] = {
    given JsonConfiguration = JsonConfiguration(JsonNaming.SnakeCase)

    Json.format[Recipe]
  }
}
