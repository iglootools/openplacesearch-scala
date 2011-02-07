package com.sirika.commons.scala

import java.io.InputStreamReader
import com.google.common.io.{CharStreams, LineProcessor, InputSupplier}
import grizzled.slf4j.Logging

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

class InputStreamReaderTransformer [T] (val readerSupplier: InputSupplier[InputStreamReader]) extends Logging {
  /**
   * Ignores comment lines and empty lines
   *
   * <p>
   * String; the line
   * Long: the lineNumber
   * </p>
   * @param f is supposed to throw Exception if a not-recoverable error happens, and a ParsingWarning/actual result otherwise
   */
  def map(f: (String, Long) => Either[ParsingWarning,T]): List[T] = {
    CharStreams.readLines(readerSupplier, new LineProcessor[List[T]]() {
      private[this] var result : List[T] = Nil
      private[this] var lineNumber = 1

      def getResult = result
      def processLine(line : String) : Boolean = {
        debug("Processing line[%d]: %s".format(lineNumber, line))

        line match {
          case s:String if s.startsWith("#") => debug("[%d] Ignoring line: comment".format(lineNumber))
          case s:String if s.isEmpty => debug("[%d] Ignoring line: empty".format(lineNumber))
          case l:String =>
            try {
              val temp = f(l, lineNumber)
              temp match {
                case Left(e) => warn("[%d] Parsing warning: %s".format(lineNumber,e.message))
                case Right(r) =>  debug("[%d] Successful result: ".format(lineNumber) + temp)
                  result ::= r
              }
            } catch {
              case e:Exception => throw new IllegalArgumentException("[%d] Unexpected error processing: %s".format(lineNumber, line), e)
            }
        }

        lineNumber+=1
        true
      }
    })
  }
}