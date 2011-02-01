package com.sirika.openplacesearch.api.geonames

trait GeonamesFeatureProvider {

  def geonamesId: Long
  def geonamesFeatureCategory: GeonamesFeatureCategory
}