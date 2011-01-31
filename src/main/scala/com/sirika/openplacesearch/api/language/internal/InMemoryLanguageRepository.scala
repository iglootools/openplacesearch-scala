package com.sirika.openplacesearch.api.language.internal

import java.io.InputStreamReader
import java.net.URL
import scala.collection.immutable.{List,Map}
import com.google.common.base.Charsets
import com.google.common.io.{Resources,CharStreams,LineProcessor, InputSupplier}
import grizzled.slf4j.Logging
import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.language.LanguageRepository


class InMemoryLanguageRepository extends LanguageRepository with Logging {
  private lazy val languages = importLanguagesFromClassPath()

  private val alpha3LookupTable : Map[String, Language] = Map(languages.map{l : Language => (l.alpha3Code, l)} : _*)
  private val alpha2LookupTable : Map[String, Language] = Map(languages filter {_.alpha2Code.isDefined} map{language => (language.alpha2Code.get, language)} : _*)

  def findAll() : Seq[Language] = languages
  def findByAlpha2Code(code: String): Option[Language] = alpha2LookupTable.get(code)
  def findByAlpha3Code(code: String): Option[Language] = alpha3LookupTable.get(code)

  private def importLanguagesFromClassPath() : List[Language ] = {
    val iso639LanguageInputStreamSupplier = Resources.newInputStreamSupplier(url("com/sirika/openplacesearch/api/language/iso639languages"))
    val iso639LanguageInputReaderSupplier = CharStreams.newReaderSupplier(iso639LanguageInputStreamSupplier, Charsets.UTF_8)
    parseLanguages(iso639LanguageInputReaderSupplier)

  }

  private def parseLanguages(readerSupplier: InputSupplier[InputStreamReader]) : List[Language] = {
    // eng      eng     en      English
    val LanguageRE = """([^\t]*)\t([^\t]*)\t([^\t]*)\t([^\t]*)""".r
    CharStreams.readLines(readerSupplier, new LineProcessor[List[Language]]() {
      private[this] var languages : List[Language] = Nil
      private[this] var lineNumber = 1

      def getResult() : List[Language] = languages
      def processLine(line : String) : Boolean = {
        // ignore first line
        if(lineNumber > 1) {
          line match {
            case "" => debug("Ignoring empty line " + lineNumber)
            case LanguageRE(alpha3Code, alphaFucked, alpha2Code, name) =>
              val alpha2CodeOption = if(Option(alpha2Code) exists (_.nonEmpty)) Some(alpha2Code) else None
              val l = Language(name, alpha3Code, alpha2CodeOption)
              languages ::= l
              debug("Language has been imported: " + l)
            case _ => throw new IllegalArgumentException("Line " + lineNumber + " cannot be processed: " + line)
          }
        } else {
          debug("Ignoring first line")
        }

        lineNumber+=1
        true
      }
    })
  }

  private def url(classpathUrl: String) : URL = {
    return Thread.currentThread().getContextClassLoader().getResource(classpathUrl)
  }
}