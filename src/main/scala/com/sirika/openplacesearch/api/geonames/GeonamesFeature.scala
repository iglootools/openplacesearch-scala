package com.sirika.openplacesearch.api.geonames

import com.sirika.openplacesearch.api.feature.StableIdProvider

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class GeonamesFeature(val geonamesId: Long,val geonamesFeatureCategory: GeonamesFeatureCategory)
  extends GeonamesFeatureProvider with StableIdProvider {
  require(geonamesFeatureCategory != null, "geonamesFeatureCategory is required")

  protected def geonamesFeature: GeonamesFeatureProvider = this

  def stableId: String = String.valueOf(geonamesFeature.geonamesId)
}