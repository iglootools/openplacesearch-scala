package com.sirika.commons.scala.io

import com.google.common.io.{InputSupplier, Closeables}
import java.io.{BufferedInputStream, InputStream}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object InputSupliers {
  def doWithInputStream[T,I <: InputStream](inputSupplier: InputSupplier[I])(f: BufferedInputStream => T): T = {
    var in: Option[BufferedInputStream] = None
    try {
      in = Some(new BufferedInputStream(inputSupplier.getInput))
      f(in.get)
    } finally {
      in.foreach(Closeables.closeQuietly _)
    }

  }
}