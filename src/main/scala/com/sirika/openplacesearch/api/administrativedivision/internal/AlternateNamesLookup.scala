package com.sirika.openplacesearch.api.administrativedivision.internal

import com.sirika.openplacesearch.api.feature.LocalizedName

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait AlternateNamesLookup {
  def getAlternateNamesFor(geonamesId: Long): List[LocalizedName]
}