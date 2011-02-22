package org.iglootools.openplacesearch.api.administrativedivision

import org.iglootools.openplacesearch.api.feature.LocalizedName

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[administrativedivision] trait AlternateNamesLookup {
  def getAlternateNamesFor(geonamesId: Long): List[LocalizedName]
}