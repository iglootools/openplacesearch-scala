package com.sirika.commons.scala.io

import java.io.{Writer, Closeable}
import com.google.common.io.{Closeables, OutputSupplier}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object OutputSuppliers {
  def doWithWriter[T,W <: Appendable with Closeable](outputSupplier: OutputSupplier[W])(f: W => T): T = {
    var out: Option[W] = None
    try {
      out = Some(outputSupplier.getOutput)
      f(out.get)
    } finally {
      out.foreach(Closeables.closeQuietly _)
    }

  }
}