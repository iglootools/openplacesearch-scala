package com.sirika.openplacesearch.api.administrativedivision

/**
 * The ISO 3166 Country code
 *
 * @see <a href="http://en.wikipedia.org/wiki/ISO_3166"> ISO 3166 </a>
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class IsoCountryCode(val alpha3Code: String, val alpha2Code: String, val numeric: Int) {
  require(Option(alpha2Code).exists {_.nonEmpty}, "alpha2Code is required")
  require(Option(alpha3Code).exists {_.nonEmpty}, "alpha3Code is required")
}