package com.sirika.openplacesearch.api.administrativedivision.internal


/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object FieldExtractors {

  def extractFieldsFromAdministrativeDivisionLine(line: String, lineNumber: Long): Array[String] = {
    val split = line.split('\t')
    split match {
      case Array(compositeCode, name, asciiName, geonamesId) => split
      case _ => throw new IllegalArgumentException("Syntax error in the input file. We are expecting the following fields: compositeCode, name, asciiName, geonamesId")
    }
  }

  def extractFieldsFromCountryLine(line: String, lineNumber: Long): List[String] = {
    val split = sanitizeLineSplit(line.split('\t'))
    split match {
      case List(isoAlpha2CountryCode,isoAlpha3CountryCode,isoNumericCountryCode,fipsCountryCode,countryName,
        capitalName,areaInSquareMeters,population,continentCode,topLevelDomain,currencyCode,currencyName,
        phonePrefix,postalCodeMask,postalCodeRegex,preferredLocales,geonamesId,neighbours, equivalentFipsCode) => split
      case _ => throw new IllegalArgumentException("Syntax error in input. It should have 19 tab-separated fields.")
    }
  }

  def extractFieldsFromAlternateNames(line: String, lineNumber: Long): Array[String] = {
    val split = line.split('\t')
    split match {
      case Array(alternateNameId, geonamesid, isolanguage, alternateName, isPreferredName, isShortName) => split
      case _ => throw new IllegalArgumentException("Syntax error in the input file. We are expecting the following fields: alternateNameId, geonamesid, isolanguage, alternateName, isPreferredName, isShortName")
    }
  }

  private def sanitizeLineSplit(line: Array[String]):List[String] = {
    val ExpectedNumberOfFields = 19
    def actualNumberOfFields = line.size
    def numberOfMissingFields = ExpectedNumberOfFields-actualNumberOfFields

    line.toList ++ List.fill(numberOfMissingFields)("")
  }
}