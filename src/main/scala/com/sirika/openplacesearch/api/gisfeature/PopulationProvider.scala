package com.sirika.openplacesearch.api.gisfeature

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait PopulationProvider {
  def population: Option[Long]
}