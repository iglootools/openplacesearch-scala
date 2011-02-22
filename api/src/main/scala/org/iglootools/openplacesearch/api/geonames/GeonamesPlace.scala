package org.iglootools.openplacesearch.api.geonames
import org.iglootools.openplacesearch.api.administrativedivision.Place

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
protected[api] object GeonamesPlace {
  def apply(gisFeature: GisFeature) = Place(
    parentAdministrativeEntityProvider=gisFeature,
    featureGeographyProvider=gisFeature,
    featureNameProvider=gisFeature,
    stableIdProvider=gisFeature)
}