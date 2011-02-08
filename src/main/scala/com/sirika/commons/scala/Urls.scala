package com.sirika.commons.scala

import java.net.URL
import com.google.common.base.Charsets
import com.google.common.io.{CharStreams, Resources, InputSupplier}
import java.io.{Reader, InputStreamReader}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object Urls {
  def classpath(classpathUrl: String) : URL = {
    return Thread.currentThread().getContextClassLoader().getResource(classpathUrl)
  }

  def toInputReaderSupplier(classpathUrl: String) : InputSupplier[InputStreamReader] = {
    val inputStreamSupplier = Resources.newInputStreamSupplier(classpath(classpathUrl))
    CharStreams.newReaderSupplier(inputStreamSupplier, Charsets.UTF_8)
  }
}