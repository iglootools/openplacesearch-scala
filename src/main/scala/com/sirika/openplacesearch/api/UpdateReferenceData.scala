package com.sirika.openplacesearch.api.language
import com.sirika.openplacesearch.api.referencedata.ReferenceData
import com.sirika.openplacesearch.api.administrativedivision.internal.FieldExtractors
import com.sirika.commons.scala.lineparser.{SkipCause, LineByLineInputStreamParser, Skip}
import com.google.common.io.{Files, InputSupplier}
import com.google.common.base.Charsets
import com.sirika.commons.scala.io.{OutputSuppliers}
import java.io.{PrintWriter, Reader, File}
import grizzled.slf4j.Logging

object UpdateReferenceData extends Logging{
  def main(args : Array[String]) : Unit = {
    val inputPath = "/home/sdalouche/workspace/samokk/geonames-data/alternateNames.txt"

    downloadReferenceFiles
    reportProgress("Extracting FeatureIDs that are related to countries and ADMx...")
    val featureIds = extractGisFeatureIds
    reportProgress("[DONE] number of featureIDS: %d".format(featureIds.size))

    reportProgress("Extracting alternate names for these FeatureIDs...")
    val alternateNames = extractAlternateNames(Files.newReaderSupplier(new File(inputPath), Charsets.UTF_8), featureIds.toSet)
    reportProgress("[DONE] number of Alternate Names: %d".format(alternateNames.size))

    reportProgress("Dumping alternate names to file: %s".format(inputPath))
    dumpAlternateNamesToFile(alternateNames)
    reportProgress("[DONE] alternate names are dumped.")
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

  def extractGisFeatureIds: Set[Int] = {
    val featureIds = extractCountryGisFeatureIds ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceData.FirstOrderAdministrativeDivisions) ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceData.SecondOrderAdministrativeDivisions)
    featureIds.toSet
  }

  def dumpAlternateNamesToFile(alternateNames: List[String]): Unit = {
    val outputPath = "/home/sdalouche/workspace/samokk/geonames-data/target/extracted-alternateNames.txt"
    OutputSuppliers.doWithWriter(Files.newWriterSupplier(new File(outputPath), Charsets.UTF_8)) { w =>
      val out = new PrintWriter(w)
      alternateNames.foreach { s =>
       out.println(s)
      }
    }
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
    /**
     * We do not care about names that do not have any language, as we cannot really do anything with them
     */
    def isIsoLanguageCode(s: String) = s.size == 2 || s.size == 3

    new LineByLineInputStreamParser(readerSupplier = input, fieldExtractor = FieldExtractors.extractFieldsFromAlternateNames).map { (fields, line, lineNumber) =>
      fields match {
        case List(alternateNameId, geonamesid, isolanguage, alternateName, isPreferredName, isShortName)
          if geonamesIds.contains(geonamesid.toInt) && isIsoLanguageCode(isolanguage) => Right(line)
        case _ => Left(Skip(SkipCause.NoResult, "AlternateName is not related to the geoname feature IDs we are looking for"))
      }
    }

  }


  def reportProgress(x : => String) = println(x)

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
