package com.sirika.openplacesearch.api.administrativedivision.internal

import com.sirika.openplacesearch.api.feature.LocalizedName
import com.sirika.openplacesearch.api.referencedata.ReferenceData
import com.sirika.openplacesearch.api.administrativedivision.Country
import com.google.common.io.InputSupplier
import java.io.Reader
import com.sirika.commons.scala.lineparser.{Skip,SkipCause, LineByLineInputStreamParser}
import com.google.inject.Inject
import com.sirika.openplacesearch.api.language.{LanguageRepository, Language}
import java.util.NoSuchElementException

//import scala.collection.mutable

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
@com.google.inject.Singleton()
protected[administrativedivision] final class InMemoryAlternateNamesLookup @Inject() (private[this] val languageRepository: LanguageRepository) extends AlternateNamesLookup {
  private lazy val localizedNames = parseLocalizedNames(ReferenceData.ExtractedAlternateNames)
  def getAlternateNamesFor(geonamesId: Long): List[LocalizedName] = localizedNames.get(geonamesId).getOrElse(List())

  private def parseLocalizedNames[R <: Reader](readerSupplier: InputSupplier[R]) : Map[Long,List[LocalizedName]] = {
    new LineByLineInputStreamParser(readerSupplier = readerSupplier, fieldExtractor = FieldExtractors.extractFieldsFromAlternateNames).map { (fields, line, lineNumber) =>
      fields match {
        case List(alternateNameId, geonamesid, isolanguage, alternateName, isPreferredName, isShortName) =>
          val language: Option[Language] = isolanguage match {
            case alpha2Code if alpha2Code.size == 2 => languageRepository.maybeGetByAlpha2Code(alpha2Code)
            case alpha3Code if alpha3Code.size == 3 => languageRepository.maybeGetByAlpha3Code(alpha3Code)
          }
          val preferred = toBoolean(isPreferredName)
          val shortName = toBoolean(isShortName)

          language match {
            case Some(l) => Right((geonamesid.toLong, LocalizedName(name=alternateName, language=l, preferred=preferred, shortName=shortName)))
            case None => Left(Skip(cause=SkipCause.Warning, message="Language with code [%s] does not exist. Alternate Name[%s] cannot be imported".format(isolanguage, alternateNameId)))
          }
      }
  }.groupBy(x => x._1).mapValues(v => v.map(_._2))
}

def toBoolean(s: String): Boolean = s.trim match {
case "1" => true
case _ => false
}
}