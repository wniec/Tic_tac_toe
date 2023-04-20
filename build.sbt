ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "Tic_tac_toe"
  )
libraryDependencies += "org.scalafx" %% "scalafx" % "19.0.0-R30"