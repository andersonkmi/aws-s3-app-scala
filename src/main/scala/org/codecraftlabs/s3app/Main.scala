package org.codecraftlabs.s3app

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.s3app.data.AwsRegion
import org.codecraftlabs.s3app.service.{AwsException, S3BucketListService}
import org.codecraftlabs.s3app.util.ArgsUtils.{parseArgs, serviceName, regionName}
import org.codecraftlabs.s3app.util.InvalidArgumentException
import org.codecraftlabs.s3app.util.ArgsValidatorUtil.validate
import org.codecraftlabs.s3app.util.ServiceType.S3_BUCKET_LIST_SERVICE

object Main extends App {
  @transient private lazy val logger: Logger = LogManager.getLogger(Main.getClass)

  logger.info("Starting app")
  try {
    val mappedArgs = parseArgs(args)
    validate(mappedArgs)

    val selectedService: String = mappedArgs.getOrElse(serviceName, "")
    selectedService match {
      case S3_BUCKET_LIST_SERVICE =>
        val region: String = mappedArgs.getOrElse(regionName, "")
        val awsRegion = if(AwsRegion.withNameOpt(region).isEmpty) AwsRegion.UsEast1 else AwsRegion.withNameOpt(region).get
        val result = S3BucketListService.list(awsRegion)
        result.get.foreach(logger.info)
      case _ => logger.warn("Not implemented yet")
    }

  } catch {
    case exception: AwsException => logger.error("Error when calling AWS", exception)
    case invalidArgException: InvalidArgumentException => logger.error("Invalid arguments", invalidArgException)
    case otherException: Throwable => logger.error("Unknown error", otherException)
  }

  logger.info("Done")
}
