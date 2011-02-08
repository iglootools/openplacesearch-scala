package com.sirika.openplacesearch.api.language
import com.sirika.openplacesearch.api.referencedata.ReferenceData
import com.sirika.openplacesearch.api.administrativedivision.internal.FieldExtractors
import com.sirika.commons.scala.lineparser.{SkipCause, LineByLineInputStreamParser, Skip}
import com.google.common.io.{Files, InputSupplier}
import com.google.common.base.Charsets
import java.io.{Reader, File, InputStreamReader}

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
    val inputPath = "/home/sdalouche/workspace/samokk/geonames-data/alternateNames.txt"
    val outputPath = "/home/sdalouche/workspace/samokk/geonames-data/target/extracted-alternateNames.txt"
    val featureIds = extractCountryGisFeatureIds ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceData.FirstOrderAdministrativeDivisions) ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceData.SecondOrderAdministrativeDivisions)
    println("number of featureIDS: %d".format(featureIds.size))
    println("number of Alternate Names: %d".format(extractAlternateNames(Files.newReaderSupplier(new File(inputPath), Charsets.UTF_8), featureIds.toSet).size))
    Files.newWriterSupplier(new File(outputPath), Charsets.UTF_8)
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

  def extractAdministrativeDivisionGisFeatureIds[R <: Reader](input: InputSupplier[R]) = {
    new LineByLineInputStreamParser(readerSupplier = input, fieldExtractor = FieldExtractors.extractFieldsFromAdministrativeDivisionLine).map { (fields, line, lineNumber) =>
      fields match {
        case Array(compositeCode, name, asciiName, geonamesId)
        => geonamesIdToResult(geonamesId)
      }
    }
  }

  def extractAlternateNames[R <: Reader](input: InputSupplier[R], geonamesIds: Set[Int]) = {
    new LineByLineInputStreamParser(readerSupplier = input, fieldExtractor = FieldExtractors.extractFieldsFromAlternateNames).map { (fields, line, lineNumber) =>
      fields match {
        case List(alternateNameId, geonamesid, isolanguage, alternateName, isPreferredName, isShortName) if geonamesIds.contains(geonamesid.toInt) => Right(line)
        case _ => Left(Skip(SkipCause.NoResult, "AlternateName is not related to the geoname feature IDs we are looking for"))
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
