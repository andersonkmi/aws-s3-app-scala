package org.codecraftlabs.s3app.util

import org.codecraftlabs.s3app.util.ArgsUtils.{bucketName, regionName, serviceName}

object ArgsValidatorUtil {
  def validateBucketCreateOrDelete(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")
    val bucketArg = args.getOrElse(bucketName, "")

    if (serviceArg.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (regionArg.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (bucketArg.isEmpty) throw InvalidArgumentException(s"Missing $bucketName argument")
  }

  def validateForBucketList(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")

    if (serviceArg.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (regionArg.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")
  }
}
