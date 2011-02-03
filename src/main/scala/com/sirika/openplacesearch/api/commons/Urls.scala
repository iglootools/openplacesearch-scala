package com.sirika.openplacesearch.api.commons

import java.net.URL

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object Urls {
  def classpath(classpathUrl: String) : URL = {
    return Thread.currentThread().getContextClassLoader().getResource(classpathUrl)
  }
}