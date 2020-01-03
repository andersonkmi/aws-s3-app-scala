package org.codecraftlabs.s3app

import org.codecraftlabs.s3app.data.S3Bucket

object Main extends App {
  val s3Bucket: S3Bucket = new S3Bucket("teste")
  println(s3Bucket.getName)
  println("Done")
}
