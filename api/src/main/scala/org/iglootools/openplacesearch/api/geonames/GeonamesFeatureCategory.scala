package org.iglootools.openplacesearch.api.geonames

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[openplacesearch] final case class GeonamesFeatureCategory(val featureClass: String, val featureCode: String) {
  require(Option(featureClass) exists {_.nonEmpty}, "featureClass is required")
  require(Option(featureCode) exists {_.nonEmpty}, "featureCode is required")
}