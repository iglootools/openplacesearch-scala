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
    val split = sanitizeLineSplit(fields=line.split('\t'),expectedNumberOfFields= 19)
    split match {
      case List(isoAlpha2CountryCode,isoAlpha3CountryCode,isoNumericCountryCode,fipsCountryCode,countryName,
        capitalName,areaInSquareMeters,population,continentCode,topLevelDomain,currencyCode,currencyName,
        phonePrefix,postalCodeMask,postalCodeRegex,preferredLocales,geonamesId,neighbours, equivalentFipsCode) => split
      case _ => throw new IllegalArgumentException("Syntax error in input. It should have 19 tab-separated fields.")
    }
  }

  def extractFieldsFromAlternateNames(line: String, lineNumber: Long): List[String] = {
    val split = sanitizeLineSplit(fields=line.split('\t'), expectedNumberOfFields=6)
    split match {
      case List(alternateNameId, geonamesid, isolanguage, alternateName, isPreferredName, isShortName) => split
      case _ => throw new IllegalArgumentException("Syntax error in the input file. We are expecting the following fields: alternateNameId, geonamesid, isolanguage, alternateName, isPreferredName, isShortName")
    }
  }

  private def sanitizeLineSplit(fields: Array[String], expectedNumberOfFields: Int):List[String] = {
    def actualNumberOfFields = fields.size
    def numberOfMissingFields = expectedNumberOfFields-actualNumberOfFields
    fields.toList ++ List.fill(numberOfMissingFields)("")
  }
}