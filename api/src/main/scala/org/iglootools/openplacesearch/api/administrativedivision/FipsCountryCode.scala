package org.iglootools.openplacesearch.api.administrativedivision

/**
 * A FIPS Country Code
 * <p>
 * A country (as defined by ISO) might not have any associated FIPS Code.
 * It also happens that several ISO countries have the same FIPS code.
 * (e.g. : Finland, and aalen island ). In these cases, Finland would have
 * the original FIPS code, and aalen island would have this code as its
 * equivalent FIPS code
 * </p>
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class FipsCountryCode(val fipsCode: Option[String] = None, val equivalentFipsCode: Option[String] = None) {
  require(fipsCode != null || equivalentFipsCode != null, "either fipsCode or equivalentFipsCode must be a non-null Option")
  List(fipsCode, equivalentFipsCode).foreach {
    for(c <- _)
      require(c.size == 2, "Fips code should have a size of 2")
  }

}