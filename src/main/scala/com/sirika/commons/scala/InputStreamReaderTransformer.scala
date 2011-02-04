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
   */
  def map(f: (String, Long) => Option[T]): List[T] = {
    CharStreams.readLines(readerSupplier, new LineProcessor[List[T]]() {
      private[this] var result : List[T] = Nil
      private[this] var lineNumber = 1

      def getResult = result
      def processLine(line : String) : Boolean = {
        debug("Processing line[%d]: %s".format(lineNumber, line))

        line match {
          case s:String if s.startsWith("#") => debug("Ignoring line [%d]: comment".format(lineNumber))
          case s:String if s.isEmpty => debug("Ignoring line[%d]: empty".format(lineNumber))
          case l:String =>
            try {
              val temp = f(l, lineNumber)
              if(temp.isDefined) {
                debug("Successful result for line[%d]: ".format(lineNumber) + temp)
                result ::= temp.get
              } else {
                debug("No result for line[%d]: ".format(lineNumber))
              }
            } catch {
              case e:Exception => throw new IllegalArgumentException("Error processing line[%d]: %s".format(lineNumber, line), e)
            }
        }

        lineNumber+=1
        true
      }
    })
  }
}