package org.iglootools.commons.scala.io

import com.google.common.io.{Closeables, OutputSupplier}
import java.io.{PrintWriter, Writer, Closeable}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object OutputSuppliers {
  def doWithWriter[T,W <: Writer](outputSupplier: OutputSupplier[W])(f: PrintWriter => T): T = {
    var out: Option[PrintWriter] = None
    try {
      out = Some(new PrintWriter(outputSupplier.getOutput))
      f(out.get)
    } finally {
      out.foreach(Closeables.closeQuietly _)
    }

  }
}