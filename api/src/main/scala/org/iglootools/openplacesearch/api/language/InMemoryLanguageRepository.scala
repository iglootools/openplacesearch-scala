package org.iglootools.openplacesearch.api.language

import scala.collection.immutable.{List,Map}
import com.google.common.io.{InputSupplier}
import grizzled.slf4j.Logging
import org.iglootools.openplacesearch.api.referencedata.ReferenceData
import org.iglootools.commons.scala.lineparser.{SkipCause, LineByLineInputStreamParser, Skip}
import java.io.{Reader}

@com.google.inject.Singleton()
protected[language] final class InMemoryLanguageRepository extends LanguageRepository with Logging {
  private val languages = parseLanguages(ReferenceData.Languages)

  private val alpha3LookupTable : Map[String, Language] = Map(languages.map{l : Language => (l.alpha3Code, l)} : _*)
  private val alpha2LookupTable : Map[String, Language] = Map(languages filter {_.alpha2Code.isDefined} map{language => (language.alpha2Code.get, language)} : _*)

  def findAll() : Seq[Language] = languages
  def getByAlpha2Code(code: String): Language = maybeGetByAlpha2Code(code).get
  def getByAlpha3Code(code: String): Language = maybeGetByAlpha3Code(code).get

  def maybeGetByAlpha2Code(code: String): Option[Language] = alpha2LookupTable.get(code.toLowerCase)
  def maybeGetByAlpha3Code(code: String): Option[Language] = alpha3LookupTable.get(code.toLowerCase)

  private def parseLanguages[R <: Reader](readerSupplier: InputSupplier[R]) : List[Language] = {
    // 4 fields separated by tabs
    // alpha3, alphaFucked, alpha2, name
    val LanguageRE =(("""([^\t]*)\t""" * 3) + """([^\t]*)""").r

    new LineByLineInputStreamParser(readerSupplier).map { (line, originalLine, lineNumber) =>
      if(lineNumber > 1) {
        line match {
          case LanguageRE(alpha3Code, alphaFucked, alpha2Code, name) =>
            val alpha2 = if(Option(alpha2Code).exists {_.nonEmpty}) Some(alpha2Code) else None
            Right(Language(name, alpha3Code, alpha2))
          case _ =>throw new IllegalArgumentException("The language is required to have a name and an alpha3code")
        }
      } else {
        Left(Skip(SkipCause.NoResult, "Ignoring first line : we assume it to be a comment"))
      }

    }
  }
}