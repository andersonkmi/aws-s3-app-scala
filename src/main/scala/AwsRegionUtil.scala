import org.codecraftlabs.s3app.data.AwsRegion
import software.amazon.awssdk.services.costandusagereport.model.AWSRegion

object AwsRegionUtil {
  def getRegion(region: AwsRegion.Value): AWSRegion = {
    AWSRegion.valueOf(region.regionCode)
  }
}
