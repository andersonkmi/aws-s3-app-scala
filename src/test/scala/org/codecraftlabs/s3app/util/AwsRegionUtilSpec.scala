package org.codecraftlabs.s3app.util

import org.codecraftlabs.s3app.data.AwsRegion.{UsEast1, UsEast2, UsWest1, UsWest2, SaEast1}
import org.scalatest.{FlatSpec, Matchers}
import software.amazon.awssdk.regions.Region.{US_EAST_1, US_EAST_2, US_WEST_1, US_WEST_2, SA_EAST_1}

class AwsRegionUtilSpec extends FlatSpec with Matchers {
  "When passing us-east-1" should "return US_EAST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(UsEast1)
    region shouldEqual US_EAST_1
  }

  "When passing us-east-2" should "return US_EAST_2 (AWS region)" in {
    val region = AwsRegionUtil.region(UsEast2)
    region shouldEqual US_EAST_2
  }

  "When passing us-west-1" should "return US_WEST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(UsWest1)
    region shouldEqual US_WEST_1
  }

  "When passing us-west-2" should "return US_WEST_2 (AWS region)" in {
    val region = AwsRegionUtil.region(UsWest2)
    region shouldEqual US_WEST_2
  }

  "When passing sa-east-1" should "return SA_EAST_1 (AWS region)" in {
    val region = AwsRegionUtil.region(SaEast1)
    region shouldEqual SA_EAST_1
  }
}
