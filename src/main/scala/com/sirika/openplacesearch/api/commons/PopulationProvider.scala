package com.sirika.openplacesearch.api.commons

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait PopulationProvider {
  def population: Option[Long]
}