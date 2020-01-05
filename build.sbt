import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "org.codecraftlabs",
      scalaVersion := "2.13.1",
      version      := "1.0.0"
    )),
    name := "aws-s3-app-scala",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += scalacticTest % Test,
    libraryDependencies += "software.amazon.awssdk" % "aws-sdk-java" % "2.10.41",
    libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.13.0",
    libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.13.0"
  )