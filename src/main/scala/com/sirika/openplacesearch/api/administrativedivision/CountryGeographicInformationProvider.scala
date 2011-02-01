package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.feature.PopulationProvider

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait CountryGeographicInformationProvider extends PopulationProvider {
  def areaInSquareKilometers: Option[Double]
}