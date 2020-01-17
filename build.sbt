import Dependencies._

lazy val excludes = jacocoExcludes in Test  :=Seq(
  "org.codecraftlabs.s3app.Main*",
  "org.codecraftlabs.s3app.service.S3Bucket*"
)

lazy val jacoco = jacocoReportSettings in test  :=JacocoReportSettings(
  "Test Coverage Report",
  None,
  JacocoThresholds (branch = 100),
  Seq(JacocoReportFormats.ScalaHTML,
    JacocoReportFormats.CSV),
  "utf-8")

val jacocoSettings = Seq(jacoco, excludes)

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
    libraryDependencies += "software.amazon.awssdk" % "aws-sdk-java" % "2.10.41" % "provided",
    libraryDependencies += "software.amazon.awssdk" % "apache-client" % "2.10.48" % "provided",
    libraryDependencies += "software.amazon.awssdk" % "netty-nio-client" % "2.10.48" % "provided",
    libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.13.0" % "provided",
    libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.13.0" % "provided",
    libraryDependencies += "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.13.0" % "provided",
    jacocoSettings
)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}