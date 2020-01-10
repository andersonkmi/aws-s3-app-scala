package org.codecraftlabs.s3app

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.s3app.data.AwsRegion
import org.codecraftlabs.s3app.service.{AwsException, S3BucketListService}
import org.codecraftlabs.s3app.util.ArgsUtils.parseArgs

object Main extends App {
  @transient private lazy val logger: Logger = LogManager.getLogger(Main.getClass)

  logger.info("Starting app")
  try {
    val mappedArgs = parseArgs(args)
    val list = S3BucketListService.list(AwsRegion.UsEast1)
    list.get.foreach(logger.info)
  } catch {
    case exception: AwsException => logger.error("Error when calling AWS", exception)
  }

  logger.info("Done")
}
