package org.codecraftlabs.s3app.service

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.s3app.data.{AwsRegion, S3Bucket}
import org.codecraftlabs.s3app.util.AwsRegionUtil.region
import software.amazon.awssdk.awscore.exception.AwsServiceException
import software.amazon.awssdk.http.apache.ApacheHttpClient
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ListBucketsRequest

import scala.jdk.CollectionConverters._

object S3BucketListService {
  @transient private lazy val logger: Logger = LogManager.getLogger(S3BucketListService.getClass)

  def list(awsRegion: AwsRegion.Value): Option[List[S3Bucket]] = {
    try {
      logger.info("Listing all buckets")
      val httpClient = ApacheHttpClient.builder().build()

      val s3Client = S3Client.builder.region(region(awsRegion)).httpClient(httpClient).build
      val request = ListBucketsRequest.builder.build
      val response = s3Client.listBuckets(request)
      val buckets = response.buckets.asScala
      Option(buckets.map(element => new S3Bucket(element.name(), element.creationDate())).toList)
    } catch  {
      case exception: AwsServiceException =>
        val message = "Error when listing buckets"
        logger.warn(message)
        throw AwsException(message, exception)
    }
  }
}
