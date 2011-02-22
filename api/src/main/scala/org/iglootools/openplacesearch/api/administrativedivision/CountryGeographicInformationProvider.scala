package org.iglootools.openplacesearch.api.administrativedivision

import org.iglootools.openplacesearch.api.feature.PopulationProvider

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait CountryGeographicInformationProvider extends PopulationProvider {
  def areaInSquareKilometers: Option[Double]
}