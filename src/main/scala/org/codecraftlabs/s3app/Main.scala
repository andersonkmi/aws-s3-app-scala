package org.codecraftlabs.s3app

import org.codecraftlabs.s3app.data.AwsRegion
import org.codecraftlabs.s3app.service.S3BucketListService

object Main extends App {
  val list = S3BucketListService.list(AwsRegion.UsEast1)
  list.get.foreach(item => println(item))
  println("Done")
}
