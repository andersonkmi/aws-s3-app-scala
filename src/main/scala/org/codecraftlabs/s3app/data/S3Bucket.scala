package org.codecraftlabs.s3app.data


class S3Bucket {
  private var name = ""
  private var region = AwsRegion.UsEast1
  private var creationDateTime = java.time.Instant.now

  def this(name: String) {
    this()
    this.name = name
  }

  def this(name: String, region: AwsRegion.Value) {
    this(name)
    this.region = region
  }

  def this(name: String, region: AwsRegion.Value, creationDateTime: java.time.Instant) {
    this(name, region)
    this.creationDateTime = creationDateTime
  }

  def getName(): String = {
    this.name
  }
}