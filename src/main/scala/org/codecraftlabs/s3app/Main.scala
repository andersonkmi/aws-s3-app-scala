package org.codecraftlabs.s3app

import org.apache.logging.log4j.{LogManager, Logger}
import org.codecraftlabs.s3app.data.AwsRegion
import org.codecraftlabs.s3app.service.S3BucketListService

object Main extends App {
  @transient private lazy val logger: Logger = LogManager.getLogger(Main.getClass)

  logger.info("Starting app")
  val list = S3BucketListService.list(AwsRegion.UsEast1)
  list.get.foreach(logger.info)

  logger.info("Done")
}
