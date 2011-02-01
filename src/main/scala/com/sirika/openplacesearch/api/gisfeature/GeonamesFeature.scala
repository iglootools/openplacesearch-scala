package com.sirika.openplacesearch.api.gisfeature

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class GeonamesFeature(val geonamesId: Long,val geonamesFeatureCategory: GeonamesFeatureCategory) extends GeonamesFeatureProvider {
  //require(geonamesId != null, "geonamesId is required")
  require(geonamesFeatureCategory != null, "geonamesFeatureCategory is required")

  protected def geonamesFeature: GeonamesFeatureProvider = this
}