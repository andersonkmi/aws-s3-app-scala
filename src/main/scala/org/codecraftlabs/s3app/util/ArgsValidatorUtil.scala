package org.codecraftlabs.s3app.util

import org.codecraftlabs.s3app.util.ArgsUtils.{bucketName, regionName, serviceName}
import org.codecraftlabs.s3app.util.ServiceType.{S3_BUCKET_CREATE_SERVICE, S3_BUCKET_DELETE_SERVICE, S3_BUCKET_LIST_SERVICE}

object ArgsValidatorUtil {
  def validateBucketCreate(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")
    val bucketArg = args.getOrElse(bucketName, "")

    if (serviceArg.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (regionArg.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (bucketArg.isEmpty) throw InvalidArgumentException(s"Missing $bucketName argument")

    if (!serviceArg.equals(S3_BUCKET_CREATE_SERVICE)) throw InvalidArgumentException(s"Invalid $serviceName argument value")
  }

  def validateBucketDelete(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")
    val bucketArg = args.getOrElse(bucketName, "")

    if (serviceArg.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (regionArg.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (bucketArg.isEmpty) throw InvalidArgumentException(s"Missing $bucketName argument")

    if (!serviceArg.equals(S3_BUCKET_DELETE_SERVICE)) throw InvalidArgumentException(s"Invalid $serviceName argument value")
  }

  def validateForBucketList(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")

    if (serviceArg.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (regionArg.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (!serviceArg.equals(S3_BUCKET_LIST_SERVICE)) throw InvalidArgumentException(s"Invalid $serviceName argument value")
  }
}
