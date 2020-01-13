package org.codecraftlabs.s3app

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.s3app.data.{AwsRegion, S3Bucket}
import org.codecraftlabs.s3app.data.AwsRegion.withNameOpt
import org.codecraftlabs.s3app.service.S3BucketCreateService.create
import org.codecraftlabs.s3app.service.S3BucketDeleteService.delete
import org.codecraftlabs.s3app.service.S3BucketListService.list
import org.codecraftlabs.s3app.service.AwsException
import org.codecraftlabs.s3app.util.ArgsUtils.{bucketName, parseArgs, regionName, serviceName}
import org.codecraftlabs.s3app.util.InvalidArgumentException
import org.codecraftlabs.s3app.util.ArgsValidatorUtil.validate
import org.codecraftlabs.s3app.util.ServiceType.{S3_BUCKET_CREATE_SERVICE, S3_BUCKET_DELETE_SERVICE, S3_BUCKET_LIST_SERVICE}

object Main {
  @transient private lazy val logger: Logger = LogManager.getLogger(Main.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("Starting app")
    try {
      val mappedArgs = parseArgs(args)
      validate(mappedArgs)

      val selectedService: String = mappedArgs.getOrElse(serviceName, "")
      selectedService match {
        case S3_BUCKET_LIST_SERVICE => listBucketService(mappedArgs)
        case S3_BUCKET_CREATE_SERVICE => createBucketService(mappedArgs)
        case S3_BUCKET_DELETE_SERVICE => deleteBucketService(mappedArgs)
        case _ => logger.warn("Not implemented yet")
      }
    } catch {
      case exception: AwsException => logger.error("Error when calling AWS", exception)
      case invalidArgException: InvalidArgumentException => logger.error("Invalid arguments", invalidArgException)
      case otherException: Throwable => logger.error("Unknown error", otherException)
    }
    logger.info("Done")
  }

  private def listBucketService(args: Map[String, String]): Unit = {
    val region: String = args.getOrElse(regionName, "")
    val awsRegion = if (withNameOpt(region).isEmpty) AwsRegion.UsEast1 else withNameOpt(region).get
    val result = list(awsRegion)
    result.get.foreach(logger.info)
  }

  private def createBucketService(args: Map[String, String]): Unit = {
    val region: String = args.getOrElse(regionName, "")
    val awsRegion = if (withNameOpt(region).isEmpty) AwsRegion.UsEast1 else withNameOpt(region).get
    val bucket: String = args.getOrElse(bucketName, "")
    val s3Bucket = new S3Bucket(bucket, awsRegion)
    create(s3Bucket)
  }

  private def deleteBucketService(args: Map[String, String]): Unit = {
    val region: String = args.getOrElse(regionName, "")
    val awsRegion = if (withNameOpt(region).isEmpty) AwsRegion.UsEast1 else withNameOpt(region).get
    val bucket: String = args.getOrElse(bucketName, "")
    val s3Bucket = new S3Bucket(bucket, awsRegion)
    delete(s3Bucket)
  }
}
