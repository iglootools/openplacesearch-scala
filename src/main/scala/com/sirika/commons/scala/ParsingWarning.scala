package com.sirika.commons.scala

/**
 * A warning during parsing
 * <p> A warning is not expected to stop the import process, but is only expected to be reported
 * </p>
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

case class ParsingWarning(val message: String)