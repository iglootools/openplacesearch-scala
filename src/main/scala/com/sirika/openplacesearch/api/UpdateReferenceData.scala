package com.sirika.openplacesearch.api.language
import com.sirika.commons.scala.{LineByLineInputStreamReader, ParsingWarning}
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
    new LineByLineInputStreamReader(ReferenceData.Countries).map { (line, lineNumber) =>
      line.split('\t') match {
        case Array(isoAlpha2CountryCode,isoAlpha3CountryCode,isoNumericCountryCode,fipsCountryCode,countryName,
        capitalName,areaInSquareMeters,population,continentCode,topLevelDomain,currencyCode,currencyName,
        phonePrefix,postalCodeMask,postalCodeRegex,preferredLocales,geonamesId,_)
        =>
          if(Option(geonamesId).exists {s => s.nonEmpty && s.toInt > 0})
            Right(geonamesId)
          else
            Left(ParsingWarning("GeonamesID is required to be a positive number, but is currently: %s".format(geonamesId)))
        case _ => throw new IllegalArgumentException("Syntax error in input. It should have 19 tab-separated fields.")
      }
    }
  }

  def reportProgress(x : Any) = println(x)
}
