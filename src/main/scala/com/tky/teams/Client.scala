package com.tky.teams

import java.io.OutputStreamWriter
import java.net.{ URL, HttpURLConnection }

object Client {

  val defaultTimeout = 5000

  def send(text: String)(implicit config: Config): Unit = {
    val json = s"""{"text": "${text}"}"""
    post(config.url, json, config.connectTimeout.getOrElse(defaultTimeout), config.readTimeout.getOrElse(defaultTimeout))
  }

  private def post(url: String, json: String, connectTimeout: Int, readTimeout: Int): String = {
    val conn = (new URL(url)).openConnection.asInstanceOf[HttpURLConnection]
    conn.setConnectTimeout(connectTimeout)
    conn.setReadTimeout(readTimeout)
    conn.setDoOutput(true);
    conn.setDoInput(true);
    conn.setRequestMethod("POST")
    conn.setRequestProperty("Content-Type", "application/json")
    val out = new OutputStreamWriter(conn.getOutputStream())
    out.write(json)
    out.flush()
    conn.connect()
    val inputStream = conn.getInputStream
    val content = io.Source.fromInputStream(inputStream).mkString
    if (inputStream != null) inputStream.close
    content
  }
}
