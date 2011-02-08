package com.sirika.commons.scala.lineparser


object SkipCause extends Enumeration {
  type SkipCause = Value
  val NoResult=Value("NoResult")
  val Warning = Value("Warning")
}
import SkipCause._

/**
 * A result that indicates that there is no result
 *
 * <p> A warning is not expected to stop the import process, but is only expected to be reported
 * </p>
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

case class Skip(val cause: SkipCause, val message: String)