package com.sirika.openplacesearch.api.language.internal

import java.io.InputStreamReader
import scala.collection.immutable.{List,Map}
import com.google.common.io.{InputSupplier}
import grizzled.slf4j.Logging
import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.language.LanguageRepository
import com.sirika.commons.scala.{LineByLineInputStreamReader, Urls, ParsingWarning}
import com.sirika.openplacesearch.api.ReferenceData

class InMemoryLanguageRepository extends LanguageRepository with Logging {
  private lazy val languages = parseLanguages(ReferenceData.Languages)

  private val alpha3LookupTable : Map[String, Language] = Map(languages.map{l : Language => (l.alpha3Code, l)} : _*)
  private val alpha2LookupTable : Map[String, Language] = Map(languages filter {_.alpha2Code.isDefined} map{language => (language.alpha2Code.get, language)} : _*)

  def findAll() : Seq[Language] = languages
  def getByAlpha2Code(code: String): Language = alpha2LookupTable.get(code.toLowerCase).get
  def getByAlpha3Code(code: String): Language = alpha3LookupTable.get(code.toLowerCase).get

  private def parseLanguages(readerSupplier: InputSupplier[InputStreamReader]) : List[Language] = {
    // 4 fields separated by tabs
    // alpha3, alphaFucked, alpha2, name
    val LanguageRE =(("""([^\t]*)\t""" * 3) + """([^\t]*)""").r

    new LineByLineInputStreamReader(readerSupplier).map { (line, lineNumber) =>
      if(lineNumber > 1) {
        line match {
          case LanguageRE(alpha3Code, alphaFucked, alpha2Code, name) =>
            val alpha2 = if(Option(alpha2Code).exists {_.nonEmpty}) Some(alpha2Code) else None
            Right(Language(name, alpha3Code, alpha2))
          case _ =>throw new IllegalArgumentException("The language is required to have a name and an alpha3code")
        }
      } else {
        Left(ParsingWarning("Ignoring first line : we assume it to be a comment"))
      }

    }
  }
}