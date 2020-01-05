package org.codecraftlabs.s3app.util

import org.codecraftlabs.s3app.data.AwsRegion
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.regions.Region.of

object AwsRegionUtil {
  def getRegion(region: AwsRegion.Value): Region = {
    of(region.regionCode)
  }
}
