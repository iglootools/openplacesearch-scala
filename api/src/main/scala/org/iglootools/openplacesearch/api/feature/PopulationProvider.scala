package org.iglootools.openplacesearch.api.feature

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait PopulationProvider {
  def population: Option[Long]
}