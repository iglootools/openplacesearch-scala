package com.sirika.openplacesearch.api.gisfeature

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class GeonamesFeature(override val geonamesId: Long,override val geonamesFeatureCategory: GeonamesFeatureCategory) extends GeonamesFeatureProvider {
  //require(geonamesId != null, "geonamesId is required")
  require(geonamesFeatureCategory != null, "geonamesFeatureCategory is required")

  protected def geonamesFeature: GeonamesFeatureProvider = this
}