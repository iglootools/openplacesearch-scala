package com.sirika.openplacesearch.api.gisfeature

trait GeonamesFeatureProvider {

  def geonamesId: Long
  def geonamesFeatureCategory: GeonamesFeatureCategory
}