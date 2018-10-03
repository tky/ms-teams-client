package com.tky.teams

case class Config(
  url: String,
  connectTimeout: Option[Int],
  readTimeout: Option[Int])

object Config {
  def url(v: String): Config = Config(url = v, connectTimeout = None, readTimeout = None)
}
