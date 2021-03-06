package org.codecraftlabs.s3app.util

import org.codecraftlabs.s3app.data.AwsRegion.withNameOpt
import org.codecraftlabs.s3app.util.ArgsUtils.{bucketName, regionName, serviceName}
import org.codecraftlabs.s3app.util.ServiceType.{S3_BUCKET_CREATE_SERVICE, S3_BUCKET_DELETE_SERVICE, S3_BUCKET_LIST_SERVICE}

object ArgsValidatorUtil {
  private val MIN_ARGS_SIZE = 2

  def validate(args: Map[String, String]): Unit = {
    if (args.size < MIN_ARGS_SIZE) throw InvalidArgumentException("Missing arguments")

    val service = args.getOrElse(serviceName, "")

    service match {
      case S3_BUCKET_LIST_SERVICE => validateForBucketList(args)
      case S3_BUCKET_DELETE_SERVICE => validateForBucketDelete(args)
      case S3_BUCKET_CREATE_SERVICE => validateForBucketCreate(args)
      case _ => throw InvalidArgumentException(s"Invalid or missing $serviceName argument")
    }
  }

  def validate(appArgs: AppArgument): Unit = {
    val service = appArgs.service

    service match {
      case S3_BUCKET_LIST_SERVICE => validateAppArgsForBucketList(appArgs)
      case S3_BUCKET_CREATE_SERVICE | S3_BUCKET_DELETE_SERVICE => validateAppArgsForBucketCreateOrDelete(appArgs)
      case _ => throw InvalidArgumentException(s"Invalid or missing $serviceName argument")
    }
  }

  private def validateAppArgsForBucketList(args: AppArgument): Unit = {
    if (args.service.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (args.region.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (withNameOpt(args.region).isEmpty) throw InvalidArgumentException(s"Invalid $regionName argument value")

  }

  private def validateAppArgsForBucketCreateOrDelete(args: AppArgument): Unit = {

    if (args.service.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (args.region.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (args.bucket.isEmpty) throw InvalidArgumentException(s"Missing $bucketName argument")

    if (withNameOpt(args.region).isEmpty) throw InvalidArgumentException(s"Invalid $regionName argument value")

  }

  private def validateForBucketCreate(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")
    val bucketArg = args.getOrElse(bucketName, "")

    if (serviceArg.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (regionArg.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (bucketArg.isEmpty) throw InvalidArgumentException(s"Missing $bucketName argument")

    if (withNameOpt(regionArg).isEmpty) throw InvalidArgumentException(s"Invalid $regionName argument value")
  }

  private def validateForBucketDelete(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")
    val bucketArg = args.getOrElse(bucketName, "")

    if (serviceArg.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (regionArg.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (bucketArg.isEmpty) throw InvalidArgumentException(s"Missing $bucketName argument")

    if (withNameOpt(regionArg).isEmpty) throw InvalidArgumentException(s"Invalid $regionName argument value")
  }

  private def validateForBucketList(args: Map[String, String]): Unit = {
    val serviceArg = args.getOrElse(serviceName, "")
    val regionArg = args.getOrElse(regionName, "")

    if (serviceArg.isEmpty) throw InvalidArgumentException(s"Missing $serviceName argument")

    if (regionArg.isEmpty) throw InvalidArgumentException(s"Missing $regionName argument")

    if (withNameOpt(regionArg).isEmpty) throw InvalidArgumentException(s"Invalid $regionName argument value")
  }
}
