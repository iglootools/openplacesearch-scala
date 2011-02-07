package com.sirika.openplacesearch.api.administrativedivision.internal

import com.sirika.commons.scala.ParsingWarning
import com.sirika.openplacesearch.api.administrativedivision.Country

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object FieldExtractors {

  def extractFieldsFromCountryLine[T](line: String) (f: (List[String]) => Either[ParsingWarning,T]) = {
     val split = sanitizeLineSplit(line.split('\t'))
     split match {
        case List(isoAlpha2CountryCode,isoAlpha3CountryCode,isoNumericCountryCode,fipsCountryCode,countryName,
        capitalName,areaInSquareMeters,population,continentCode,topLevelDomain,currencyCode,currencyName,
        phonePrefix,postalCodeMask,postalCodeRegex,preferredLocales,geonamesId,neighbours, equivalentFipsCode)
        => f(split)
        case _ => throw new IllegalArgumentException("Syntax error in input. It should have 19 tab-separated fields.")
      }
  }

  private def sanitizeLineSplit(line: Array[String]):List[String] = {
    val ExpectedNumberOfFields = 19
    def actualNumberOfFields = line.size
    def numberOfMissingFields = ExpectedNumberOfFields-actualNumberOfFields

    line.toList ++ List.fill(numberOfMissingFields)("")
  }
}