package org.iglootools.openplacesearch.api.geonames

import org.iglootools.openplacesearch.api.feature.StableIdProvider

protected[api] trait GeonamesFeatureProvider extends StableIdProvider {

  def geonamesId: Long
  def geonamesFeatureCategory: GeonamesFeatureCategory
}