package com.sirika.openplacesearch.api.gisfeature

trait GeonamesFeatureProvider {
  self: {
    def geonamesFeature: GeonamesFeatureProvider
  } =>

  def geonamesId: Long = geonamesFeature.geonamesId
  def geonamesFeatureCategory: GeonamesFeatureCategory = geonamesFeature.geonamesFeatureCategory
}