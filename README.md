# Simple Recipe Service

Simple service to manage recipes. It allows to create, read, update and delete recipes.

## Dependency
1. Scala 3.3.4 w/ Java 21
2. Play Framework 3.0.5
3. Database Access: Anorm 2.7.0
4. Database Engine: H2

## How to Run
### Local
1. Install `sbt` on your machine.
2. Run the following command in the root directory of the project.
    ```bash
    sbt run
    ```
### Docker
TBA

## API
### Create Recipe
```bash
curl --location 'http://localhost:9000/recipes' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Tomato Soup",
    "making_time": "15 min",
    "serves": "5 people",
    "ingredients": "onion, tomato, seasoning, water",
    "cost": 450          
}'
```
### Get Recipe
```bash
curl --location 'http://localhost:9000/recipes/1'
```
### Get All Recipes
```bash
curl --location 'http://localhost:9000/recipes'
```
### Update Recipe
```bash
curl --location --request PATCH 'http://localhost:9000/recipes/2' \
--header 'Content-Type: application/json' \
--data '{
    "title": "Tomato Soup",
    "making_time": "15 min",
    "serves": "5 people",
    "ingredients": "onion, tomato, seasoning, water",
    "cost": 450           
}'
```
### Delete Recipe
```bash
curl --location --request DELETE 'http://localhost:9000/recipes/1'
```
