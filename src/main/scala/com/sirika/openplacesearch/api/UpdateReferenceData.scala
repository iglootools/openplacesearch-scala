package com.sirika.openplacesearch.api.language
import com.sirika.openplacesearch.api.language.internal.InMemoryLanguageRepository
import com.sirika.commons.scala.LineByLineInputStreamReader
import com.sirika.openplacesearch.api.referencedata.ReferenceData

object UpdateReferenceData {
  def main(args : Array[String]) : Unit = {
    downloadReferenceFiles
    val featureIds = extractGisFeatureIds
  }

  def downloadReferenceFiles = {
    reportProgress("Downloading reference files...")
    reportProgress("[skip] SKipping iso639Languages download: not implemented yet.")
    reportProgress("[skip] SKipping countries download: not implemented yet.")
    reportProgress("[skip] SKipping admin1Codes download: not implemented yet.")
    reportProgress("[skip] SKipping admin2Codes download: not implemented yet.")
    reportProgress("[skip] SKipping features download: not implemented yet.")
    reportProgress("[skip] SKipping alternateNames download: not implemented yet.")
  }

  def extractGisFeatureIds: List[Int] = {
    extractCountryGisFeatures
    null
  }

  def extractCountryGisFeatures = {
//    new LineByLineInputStreamReader(ReferenceData.Countries).map { (line, lineNumber) =>
//      sanitizeLineSplit(line.split('\t')) match {
//        case List(isoAlpha2CountryCode,isoAlpha3CountryCode,isoNumericCountryCode,fipsCountryCode,countryName,
//        capitalName,areaInSquareMeters,population,continentCode,topLevelDomain,currencyCode,currencyName,
//        phonePrefix,postalCodeMask,postalCodeRegex,preferredLocales,geonamesId,neighbours, equivalentFipsCode)
//        =>
//      }
//    }
  }

  def reportProgress(x : Any) = println(x)
}
