name := """simple-play-recipe"""
organization := "dev.andreasarf"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "3.3.4"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
libraryDependencies ++= Seq(
  jdbc,
  evolutions,
  "com.h2database" % "h2" % "2.3.232",
  "org.playframework.anorm" %% "anorm" % "2.7.0"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "dev.andreasarf.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "dev.andreasarf.binders._"
