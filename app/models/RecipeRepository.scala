package models

import anorm.*
import anorm.SqlParser.get
import play.api.db.DBApi

import java.time.LocalDateTime
import javax.inject.{Inject, Singleton}

@Singleton
class RecipeRepository @Inject(dbapi: DBApi)(implicit ec: DatabaseExecutionContext) {

  private val db = dbapi.database("default")

  private val simple = {
    get[Option[Long]]("recipes.id") ~
      get[String]("recipes.title") ~
      get[String]("recipes.making_time") ~
      get[String]("recipes.serves") ~
      get[String]("recipes.ingredients") ~
      get[Int]("recipes.cost") ~
      get[Option[LocalDateTime]]("recipes.created_at") ~
      get[Option[LocalDateTime]]("recipes.updated_at") map {
      case id ~ title ~ makingTime ~ serves ~ ingredients ~ cost ~ createdAt ~ updatedAt =>
        Recipe(id, title, makingTime, serves, ingredients, cost, createdAt, updatedAt)
    }
  }

  def insert(recipe: Recipe): Option[Long] = {
    db.withConnection { implicit connection =>
      SQL(
        """
          insert into recipes (title, making_time, serves, ingredients, cost)
          values ({title}, {makingTime}, {serves}, {ingredients}, {cost})
        """
      ).bind(recipe).executeInsert()
    }
  }

  def findById(id: Long): Option[Recipe] = {
    db.withConnection { implicit connection =>
      SQL(
        """
          select * from recipes where id = {id}
        """
      ).on("id" -> id).as(simple.singleOpt)
    }
  }

  def findAll(): Seq[Recipe] = {
    db.withConnection { implicit connection =>
      SQL(
        """
          select * from recipes
        """
      ).as(simple.*)
    }
  }

  def update(id: Long, recipe: Recipe): Int = {
    db.withConnection { implicit connection =>
      SQL(
        """
          update recipes
          set title = {title},
              making_time = {makingTime},
              serves = {serves},
              ingredients = {ingredients},
              cost = {cost}
          where id = {id}
        """
      ).bind(recipe).executeUpdate()
    }
  }

  def delete(id: Long): Int = {
    db.withConnection { implicit connection =>
      SQL(
        """
          delete from recipes where id = {id}
        """
      ).on("id" -> id).executeUpdate()
    }
  }

}
