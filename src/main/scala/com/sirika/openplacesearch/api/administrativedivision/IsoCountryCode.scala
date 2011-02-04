package com.sirika.openplacesearch.api.administrativedivision

/**
 * The ISO 3166 Country code
 *
 * @see <a href="http://en.wikipedia.org/wiki/ISO_3166"> ISO 3166 </a>
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class IsoCountryCode(val alpha3Code: String, val alpha2Code: String, val numeric: Int) {
  require(Option(alpha2Code).exists {c => c.nonEmpty && c.size == 2}, "alpha2Code is required, with an expected size of 2: %s".format(alpha2Code))
  require(Option(alpha3Code).exists {c => c.nonEmpty && c.size == 3}, "alpha3Code is required, with an expected size of 3: %s".format(alpha3Code))
}