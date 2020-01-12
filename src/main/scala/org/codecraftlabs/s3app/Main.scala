package org.codecraftlabs.s3app

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.s3app.data.{AwsRegion, S3Bucket}
import org.codecraftlabs.s3app.data.AwsRegion.withNameOpt
import org.codecraftlabs.s3app.service.S3BucketListService.list
import org.codecraftlabs.s3app.service.{AwsException, S3BucketCreateService}
import org.codecraftlabs.s3app.util.ArgsUtils.{bucketName, parseArgs, regionName, serviceName}
import org.codecraftlabs.s3app.util.InvalidArgumentException
import org.codecraftlabs.s3app.util.ArgsValidatorUtil.validate
import org.codecraftlabs.s3app.util.ServiceType.{S3_BUCKET_CREATE_SERVICE, S3_BUCKET_LIST_SERVICE}

object Main extends App {
  @transient private lazy val logger: Logger = LogManager.getLogger(Main.getClass)

  logger.info("Starting app")
  try {
    val mappedArgs = parseArgs(args)
    validate(mappedArgs)

    val selectedService: String = mappedArgs.getOrElse(serviceName, "")
    // todo: include delete service
    selectedService match {
      case S3_BUCKET_LIST_SERVICE =>
        val region: String = mappedArgs.getOrElse(regionName, "")
        val awsRegion = if(withNameOpt(region).isEmpty) AwsRegion.UsEast1 else withNameOpt(region).get
        val result = list(awsRegion)
        result.get.foreach(logger.info)
      case S3_BUCKET_CREATE_SERVICE =>
        val region: String = mappedArgs.getOrElse(regionName, "")
        val awsRegion = if(withNameOpt(region).isEmpty) AwsRegion.UsEast1 else withNameOpt(region).get
        val bucket: String = mappedArgs.getOrElse(bucketName, "")
        val s3Bucket = new S3Bucket(bucket, awsRegion)
        S3BucketCreateService.create(s3Bucket)
      case _ => logger.warn("Not implemented yet")
    }
  } catch {
    case exception: AwsException => logger.error("Error when calling AWS", exception)
    case invalidArgException: InvalidArgumentException => logger.error("Invalid arguments", invalidArgException)
    case otherException: Throwable => logger.error("Unknown error", otherException)
  }

  logger.info("Done")
}
