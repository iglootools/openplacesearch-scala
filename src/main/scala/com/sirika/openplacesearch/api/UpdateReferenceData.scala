package com.sirika.openplacesearch.api.language
import com.sirika.openplacesearch.api.referencedata.ReferenceData
import com.sirika.openplacesearch.api.administrativedivision.internal.FieldExtractors
import java.io.InputStreamReader
import com.google.common.io.InputSupplier
import com.sirika.commons.scala.lineparser.{SkipCause, LineByLineInputStreamParser, Skip}

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

    val featureIds = extractCountryGisFeatureIds ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceData.FirstOrderAdministrativeDivisions) ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceData.SecondOrderAdministrativeDivisions)

    println(featureIds.toSet)
    null
  }

  def extractCountryGisFeatureIds = {
    new LineByLineInputStreamParser(readerSupplier = ReferenceData.Countries, fieldExtractor = FieldExtractors.extractFieldsFromCountryLine).map { (fields, line, lineNumber) =>
      fields match {
        case List(isoAlpha2CountryCode,isoAlpha3CountryCode,isoNumericCountryCode,fipsCountryCode,countryName,
        capitalName,areaInSquareMeters,population,continentCode,topLevelDomain,currencyCode,currencyName,
        phonePrefix,postalCodeMask,postalCodeRegex,preferredLocales,geonamesId,neighbours, equivalentFipsCode)
        =>  geonamesIdToResult(geonamesId)
      }
    }
  }

  def extractAdministrativeDivisionGisFeatureIds(input: InputSupplier[InputStreamReader]) = {
    new LineByLineInputStreamParser(readerSupplier = input, fieldExtractor = FieldExtractors.extractFieldsFromAdministrativeDivisionLine).map { (fields, line, lineNumber) =>
      fields match {
        case Array(compositeCode, name, asciiName, geonamesId)
        => geonamesIdToResult(geonamesId)
      }
    }
  }

  def extractAlternateNames(input: InputSupplier[InputStreamReader], geonamesIds: Set[Int]) = {
    new LineByLineInputStreamParser(readerSupplier = ReferenceData.Countries, fieldExtractor = FieldExtractors.extractFieldsFromAlternateNames).map { (fields, line, lineNumber) =>
      fields match {
        case Array(alternateNameId, geonamesid, isolanguage, alternateName, isPreferredName, isShortName) if geonamesIds.contains(geonamesid.toInt) => Right(line)
        case _ => Left(Skip(SkipCause.NoResult, "nothing to say"))
      }
    }

  }


  def reportProgress(x : Any) = println(x)

  def geonamesIdToResult(id: String): Either[Skip, Int] = {
    if(correctGeonamesId(id))
      Right(id.toInt)
    else
      Left(Skip(SkipCause.Warning, "GeonamesID is required to be a positive number, but is currently: %s".format(id)))
  }
  def correctGeonamesId(id: String): Boolean = {
    Option(id).exists {s => s.nonEmpty && s.toInt > 0}
  }
}
