package org.codecraftlabs.s3app.util

import org.codecraftlabs.s3app.util.ArgsValidatorUtil.{validateBucketCreateOrDelete, validateForBucketList}
import org.scalatest.{FlatSpec, Matchers}

class ArgsValidatorUtilSpec extends FlatSpec with Matchers {
  "All args for bucket create or delete" should "work" in {
    val args = Map("--service" -> "listBucket", "--region" -> "us-east-1", "--bucket" -> "test")

    validateBucketCreateOrDelete(args)
  }

  "Missing --service for bucket create or delete" should "throw InvalidArgumentException" in {
    val args = Map("--region" -> "us-east-1", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validateBucketCreateOrDelete(args)
    exception.getMessage should equal("Missing --service argument")
  }

  "Missing --region for bucket create or delete" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "listBucket", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validateBucketCreateOrDelete(args)
    exception.getMessage should equal("Missing --region argument")
  }

  "Missing --bucket for bucket create or delete" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "listBucket", "--region" -> "us-east-1")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validateBucketCreateOrDelete(args)
    exception.getMessage should equal("Missing --bucket argument")
  }

  "All args for bucket list" should "work" in {
    val args = Map("--service" -> "listBucket", "--region" -> "us-east-1")

    validateForBucketList(args)
  }

  "Missing --service for bucket list" should "throw InvalidArgumentException" in {
    val args = Map("--region" -> "us-east-1", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validateForBucketList(args)
    exception.getMessage should equal("Missing --service argument")
  }

  "Missing --region for bucket list" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "listBucket", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validateForBucketList(args)
    exception.getMessage should equal("Missing --region argument")
  }
}
