package org.codecraftlabs.s3app.data

object AwsRegion extends Enumeration {
  protected case class Val(code: String, description: String) extends super.Val {
    def regionCode: String = code
    def regionDescription: String = description
  }

  import scala.language.implicitConversions
  implicit def valueToAwsRegionVal(x: Value): Val = x.asInstanceOf[Val]

  val UsEast1 = Val("us-east-1", "US East (N. Virginia)")
}
