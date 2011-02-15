package com.sirika.openplacesearch.api
import com.sirika.openplacesearch.api.administrativedivision.internal.FieldExtractors
import com.sirika.commons.scala.lineparser.{SkipCause, LineByLineInputStreamParser, Skip}
import com.google.common.io.{Files, InputSupplier}
import com.google.common.base.Charsets
import com.sirika.commons.scala.io.{OutputSuppliers}
import grizzled.slf4j.Logging
import java.io._

object ReferenceFiles {
  val DownloadDirectoryRelativePath = "referencedata" + File.separator + "downloads"
  val TargetDirectoryRelativePath = "referencedata" + File.separator + "target"
  val CountriesFilename = "countryInfo.txt"
  val LanguagesFilename = "iso-languagecodes.txt"
  val Admin1Filename = "admin1CodesASCII.txt"
  val Admin2Filename = "admin2Codes.txt"
  val AlternateNamesFilename = "alternateNames.txt"
  val GisFeaturesFilename = "allCountries.txt"
  val ExtractedAlternateNamesFilename = "extracted-alternateNames.txt"

  def referenceFile(filename: String) = new File(new File(System.getProperty("user.dir")), DownloadDirectoryRelativePath + File.separator + filename)
  def targetFile(filename: String) = new File(new File(System.getProperty("user.dir")), TargetDirectoryRelativePath + File.separator + filename)
  def readerSupplier(filename: String) = Files.newReaderSupplier(referenceFile(filename), Charsets.UTF_8)
  def writerSupplier(filename: String) = Files.newWriterSupplier(targetFile(filename), Charsets.UTF_8)
}

object UpdateReferenceData extends Logging{
  def main(args : Array[String]) : Unit = {
    info("Update Reference Data")

    downloadReferenceFiles
    reportProgress("Extracting FeatureIDs that are related to countries and ADMx...")
    val featureIds = extractGisFeatureIds
    reportProgress("    [DONE] number of featureIDS: %d".format(featureIds.size))

    reportProgress("Extracting alternate names for these FeatureIDs...")
    val alternateNames = extractAlternateNames(ReferenceFiles.readerSupplier(ReferenceFiles.AlternateNamesFilename), featureIds.toSet)
    reportProgress("    [DONE] number of Alternate Names: %d".format(alternateNames.size))

    reportProgress("Dumping alternate names to file")
    dumpAlternateNamesToFile(alternateNames)
    reportProgress("    [DONE] alternate names have been dumped to disk")
  }

  def downloadReferenceFiles = {
    reportProgress("Downloading reference files...")
    reportProgress("    [skip] Skipping iso639Languages download: not implemented yet. (done previously in bash)")
    reportProgress("    [skip] Skipping countries download: not implemented yet. (done previously in bash)")
    reportProgress("    [skip] Skipping admin1Codes download: not implemented yet. (done previously in bash)")
    reportProgress("    [skip] Skipping admin2Codes download: not implemented yet. (done previously in bash)")
    reportProgress("    [skip] Skipping features download: not implemented yet. (done previously in bash)")
    reportProgress("    [skip] Skipping alternateNames download: not implemented yet. (done previously in bash)")
  }

  def extractGisFeatureIds: Set[Int] = {
    val featureIds = extractCountryGisFeatureIds ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceFiles.readerSupplier(ReferenceFiles.Admin1Filename)) ++
      extractAdministrativeDivisionGisFeatureIds(ReferenceFiles.readerSupplier(ReferenceFiles.Admin2Filename))
    featureIds.toSet
  }

  def dumpAlternateNamesToFile(alternateNames: List[String]): Unit = {
    OutputSuppliers.doWithWriter(ReferenceFiles.writerSupplier(ReferenceFiles.ExtractedAlternateNamesFilename)) { w =>
      alternateNames.foreach { s =>
        w.println(s)
      }
    }
  }

  def extractCountryGisFeatureIds = {
    new LineByLineInputStreamParser(readerSupplier = ReferenceFiles.readerSupplier(ReferenceFiles.CountriesFilename), fieldExtractor = FieldExtractors.extractFieldsFromCountryLine).map { (fields, line, lineNumber) =>
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
    if(isCorrectGeonamesId(id))
      Right(id.toInt)
    else
      Left(Skip(SkipCause.Warning, "GeonamesID is required to be a positive number, but is currently: %s".format(id)))
  }

  def isCorrectGeonamesId(id: String): Boolean = {
    Option(id).exists {s => s.nonEmpty && s.toInt > 0}
  }
}
