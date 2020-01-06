package org.codecraftlabs.s3app.util

case class InvalidArgumentException(private val message: String = "",
                                    private val cause: Throwable = None.orNull) extends Exception (message, cause)
