package com.sirika.openplacesearch.api.referencedata

import com.sirika.commons.scala.Urls

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object ReferenceData {
  private val pkgPath = "com/sirika/openplacesearch/api/referencedata"
  val LanguagesPath = pkgPath + "/iso639languages"
  val CountriesPath = pkgPath +  "/countries"
  val FirstOrderAdministrativeDivisionsPath = pkgPath + "/admin1CodesASCII"
  val SecondOrderAdministrativeDivisionsPath = pkgPath + "/admin2Codes"
  val ExtractedAlternateNamesPath = pkgPath + "/extractedAlternateNames"
  def Languages = Urls.toInputReaderSupplier(ReferenceData.LanguagesPath)
  def Countries = Urls.toInputReaderSupplier(ReferenceData.CountriesPath)
  def FirstOrderAdministrativeDivisions = Urls.toInputReaderSupplier(ReferenceData.FirstOrderAdministrativeDivisionsPath)
  def SecondOrderAdministrativeDivisions = Urls.toInputReaderSupplier(ReferenceData.SecondOrderAdministrativeDivisionsPath)
  def ExtractedAlternateNames = Urls.toInputReaderSupplier(ReferenceData.ExtractedAlternateNamesPath)
}