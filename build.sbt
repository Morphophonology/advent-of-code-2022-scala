ThisBuild / organization := "org.morphophonology"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "3.2.1"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code",

    // project settings
    fork := true
  )
