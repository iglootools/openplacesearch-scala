package com.sirika.openplacesearch.api.geonames
import com.sirika.openplacesearch.api.administrativedivision.Place

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
object GeonamesPlace {
  def apply(gisFeature: GisFeature) = Place(
    parentAdministrativeEntityProvider=gisFeature,
    featureGeographyProvider=gisFeature,
    featureNameProvider=gisFeature,
    stableIdProvider=gisFeature)
}