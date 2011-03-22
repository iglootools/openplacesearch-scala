package org.iglootools.openplacesearch.api.referencedata

import org.iglootools.commons.scala.io.Urls

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[api] object ReferenceData {
  private val pkgPath = "org/iglootools/openplacesearch/api/referencedata"
  val LanguagesPath = pkgPath + "/iso639languages"
  val CountriesPath = pkgPath +  "/countries"
  val FirstOrderAdministrativeDivisionsPath = pkgPath + "/admin1CodesASCII"
  val SecondOrderAdministrativeDivisionsPath = pkgPath + "/admin2Codes"
  val ExtractedAlternateNamesPath = pkgPath + "/extractedAlternateNames"
  val GeonamesIDAliases = pkgPath + "/geonames-aliases.xml"
  def Languages = Urls.toInputReaderSupplier(ReferenceData.LanguagesPath)
  def Countries = Urls.toInputReaderSupplier(ReferenceData.CountriesPath)
  def FirstOrderAdministrativeDivisions = Urls.toInputReaderSupplier(ReferenceData.FirstOrderAdministrativeDivisionsPath)
  def SecondOrderAdministrativeDivisions = Urls.toInputReaderSupplier(ReferenceData.SecondOrderAdministrativeDivisionsPath)
  def ExtractedAlternateNames = Urls.toInputReaderSupplier(ReferenceData.ExtractedAlternateNamesPath)
}