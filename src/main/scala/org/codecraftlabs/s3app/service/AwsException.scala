package org.codecraftlabs.s3app.service

final case class AwsException(private val message: String = "", private val cause: Throwable = None.orNull) extends Exception (message, cause)
