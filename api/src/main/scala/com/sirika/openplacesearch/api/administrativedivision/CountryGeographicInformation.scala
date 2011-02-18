package com.sirika.openplacesearch.api.administrativedivision

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class CountryGeographicInformation(val population: Option[Long]=None, val areaInSquareKilometers: Option[Double]=None) extends CountryGeographicInformationProvider {

}