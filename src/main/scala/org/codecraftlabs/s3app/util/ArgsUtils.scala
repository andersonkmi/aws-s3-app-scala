package org.codecraftlabs.s3app.util

import scala.collection.mutable

object ArgsUtils {
  val serviceName: String = "--service"
  val regionName: String = "--region"
  val bucketName: String = "--bucket"

  def parseArgs(args: Array[String]): Map[String, String] = {
    val result = mutable.Map[String, String]()
    var currentKey = ""
    for(index <- args.indices) {
      val currentItem = args(index)
      if(currentItem.startsWith("--")) {
        currentKey = currentItem
      } else {
        result(currentKey) = currentItem
      }
    }
    result.toMap
  }

  def buildArg(args: Map[String, String]): AppArgument = {
    val bucket = args.getOrElse(bucketName, "")
    val region = args.getOrElse(regionName, "")
    val service = args.getOrElse(serviceName, "")
    AppArgument(service, region, bucket)
  }
}