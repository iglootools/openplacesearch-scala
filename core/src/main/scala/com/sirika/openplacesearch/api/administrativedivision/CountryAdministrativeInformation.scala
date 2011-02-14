package com.sirika.openplacesearch.api.administrativedivision

import com.ibm.icu.util.ULocale

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class CountryAdministrativeInformation(
  val preferredLocales: List[ULocale]  = List(),
  val topLevelDomain: Option[String] = None,
  val phonePrefix: Option[String]  = None,
  val postalCodeRegex: Option[String]  = None,
  val postalCodeMask: Option[String] = None) extends CountryAdministrativeInformationProvider {
  require(preferredLocales != null, "preferredLocales must be a non-null list")
  require(topLevelDomain != null, "topLevelDomain must be a non-null option")
  require(phonePrefix != null, "phonePrefix must be a non-null option")
  require(postalCodeRegex != null, "postalCodeRegex must be a non-null option")
  require(postalCodeMask != null, "postalCodeMask must be a non-null option")
}