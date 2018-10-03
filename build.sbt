name := """ms-teams-client"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % "test"
  )
