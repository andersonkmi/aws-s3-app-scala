package org.codecraftlabs.s3app.util

import org.codecraftlabs.s3app.util.ArgsValidatorUtil.validate
import org.scalatest.{FlatSpec, Matchers}

class ArgsValidatorUtilSpec extends FlatSpec with Matchers {
  "All args for bucket create" should "work" in {
    val args = Map("--service" -> "createBucket", "--region" -> "us-east-1", "--bucket" -> "test")

    validate(args)
  }

  "Missing --service for bucket create" should "throw InvalidArgumentException" in {
    val args = Map("--region" -> "us-east-1", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid or missing --service argument")
  }

  "Invalid --service value for bucket create" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "bla", "--region" -> "us-east-1", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid or missing --service argument")
  }

  "Missing --region for bucket create" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "createBucket", "--bucket" -> "test", "--region" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid --region argument value")
  }

  "Invalid --region for bucket create" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "createBucket", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Missing --region argument")
  }

  "Missing --bucket for bucket create" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "createBucket", "--region" -> "us-east-1")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Missing --bucket argument")
  }

  "All args for bucket delete" should "work" in {
    val args = Map("--service" -> "deleteBucket", "--region" -> "us-east-1", "--bucket" -> "test")

    validate(args)
  }

  "Missing --service for bucket delete" should "throw InvalidArgumentException" in {
    val args = Map("--region" -> "us-east-1", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid or missing --service argument")
  }

  "Invalid --service value for bucket delete" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "bla", "--region" -> "us-east-1", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid or missing --service argument")
  }

  "Missing --region for bucket delete" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "deleteBucket", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Missing --region argument")
  }

  "Missing --bucket for bucket delete" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "deleteBucket", "--region" -> "us-east-1")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Missing --bucket argument")
  }

  "Invalid --region for bucket delete" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "deleteBucket", "--bucket" -> "test", "--region" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid --region argument value")
  }

  "All args for bucket list" should "work" in {
    val args = Map("--service" -> "listBucket", "--region" -> "us-east-1")

    validate(args)
  }

  "Missing --service for bucket list" should "throw InvalidArgumentException" in {
    val args = Map("--region" -> "us-east-1", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid or missing --service argument")
  }

  "Missing --region for bucket list" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "listBucket", "--bucket" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Missing --region argument")
  }

  "Invalid --service value for bucket list" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "bla", "--region" -> "us-east-1")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid or missing --service argument")
  }

  "Invalid --region for bucket list" should "throw InvalidArgumentException" in {
    val args = Map("--service" -> "listBucket", "--bucket" -> "test", "--region" -> "test")

    val exception: InvalidArgumentException = the [InvalidArgumentException] thrownBy validate(args)
    exception.getMessage should equal("Invalid --region argument value")
  }
}
