package com.sirika.commons.scala

import java.net.URL

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object Urls {
  def classpath(classpathUrl: String) : URL = {
    return Thread.currentThread().getContextClassLoader().getResource(classpathUrl)
  }
}