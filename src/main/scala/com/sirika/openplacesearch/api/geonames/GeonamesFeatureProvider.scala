package com.sirika.openplacesearch.api.geonames

import com.sirika.openplacesearch.api.feature.StableIdProvider

trait GeonamesFeatureProvider extends StableIdProvider {

  def geonamesId: Long
  def geonamesFeatureCategory: GeonamesFeatureCategory
}