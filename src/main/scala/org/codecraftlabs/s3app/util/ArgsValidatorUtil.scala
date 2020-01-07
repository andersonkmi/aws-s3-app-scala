package org.codecraftlabs.s3app.util

import org.codecraftlabs.s3app.util.ArgsUtils.{bucketName, regionName, serviceName}

object ArgsValidatorUtil {
  def validateForBucketCreation(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")
    val bucketArg = args.getOrElse(bucketName, "")

    if (serviceArg.isEmpty || regionArg.isEmpty || bucketArg.isEmpty) throw InvalidArgumentException("Missing args")
  }

  def validateForBucketListing(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")

    if (serviceArg.isEmpty || regionArg.isEmpty) throw InvalidArgumentException("Missing args")
  }

  def validateForBucketDelete(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")
    val bucketArg = args.getOrElse(bucketName, "")

    if (serviceArg.isEmpty || regionArg.isEmpty || bucketArg.isEmpty) throw InvalidArgumentException("Missing args")
  }
}
