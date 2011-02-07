package com.sirika.openplacesearch.api

import com.sirika.commons.scala.Urls

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object ReferenceData {
  val LanguagesPath = "com/sirika/openplacesearch/api/referencedata/iso639languages"
  val CountriesPath = "com/sirika/openplacesearch/api/referencedata/countries"
  val FirstOrderAdministrativeDivisionsPath = "com/sirika/openplacesearch/api/referencedata/admin1CodesASCII"
  val SecondOrderAdministrativeDivisionsPath = "com/sirika/openplacesearch/api/referencedata/admin2Codes"
  def Languages = Urls.toInputReaderSupplier(ReferenceData.LanguagesPath)
  def Countries = Urls.toInputReaderSupplier(ReferenceData.CountriesPath)
  def FirstOrderAdministrativeDivisions = Urls.toInputReaderSupplier(ReferenceData.FirstOrderAdministrativeDivisionsPath)
  def SecondOrderAdministrativeDivisions = Urls.toInputReaderSupplier(ReferenceData.SecondOrderAdministrativeDivisionsPath)
}