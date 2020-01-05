package org.codecraftlabs.s3app.service

import java.util.logging.Logger

import org.codecraftlabs.s3app.data.{AwsRegion, S3Bucket}
import org.codecraftlabs.s3app.util.AwsRegionUtil.region
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.ListBucketsRequest

import scala.jdk.CollectionConverters._

object S3BucketListService {
  @transient private lazy val logger: Logger = Logger.getLogger(getClass.getName)

  def list(awsRegion: AwsRegion.Value): Option[List[S3Bucket]] = {
    logger.info("Listing all buckets")
    val s3Client = S3Client.builder.region(region(awsRegion)).build
    val request = ListBucketsRequest.builder.build
    val response = s3Client.listBuckets(request)
    val buckets = response.buckets.asScala
    Option(buckets.map(element => new S3Bucket(element.name(), element.creationDate())).toList)
  }
}
