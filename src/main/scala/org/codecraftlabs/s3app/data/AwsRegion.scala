package org.codecraftlabs.s3app.data

object AwsRegion extends Enumeration {
  protected case class Val(code: String, description: String) extends super.Val {
    def regionCode: String = code
    def regionDescription: String = description
  }

  import scala.language.implicitConversions
  implicit def valueToAwsRegionVal(x: Value): Val = x.asInstanceOf[Val]

  val UsEast1 = Val("us-east-1", "US East (N. Virginia)")
  val UsEast2 = Val("us-east-2", "US East (Ohio)")
  val UsWest1 = Val("us-west-1", "US West (N. California)")
  val UsWest2 = Val("us-west-2", "US West (Oregon)")
  val SaEast1 = Val("sa-east-1", "South America (Sao Paulo)")
  val CaCentral1 = Val("ca-central-1", "Canada (Central)")
  val EuNorth1 = Val("eu-north-1", "Europe (Stockholm)")
  val EuCentral1 = Val("eu-central-1", "Europe (Frankfurt)")
  val EuWest1 = Val("eu-west-1", "Europe (Ireland)")
  val EuWest2 = Val("eu-west-2", "Europe (London)")
  val EuWest3 = Val("eu-west-3", "Europe (Paris)")
  val ApNorthEast1 = Val("ap-northeast-1", "Asia Pacific (Tokyo)")
  val ApNorthEast2 = Val("ap-northeast-2", "Asia Pacific (Seoul)")
  val ApNorthEast3 = Val("ap-northeast-3", "Asia Pacific (Osaka-Local)")
  val ApSouthEast1 = Val("ap-southeast-1", "Asia Pacific (Singapore)")
  val ApSouthEast2 = Val("ap-southeast-2", "Asia Pacific (Sydney)")
  val ApSouth1 = Val("ap-south-1", "Asia Pacific (Mumbai)")
  val ApEast1 = Val("ap-east-1", "Asia Pacific (Hong Kong)")
}
