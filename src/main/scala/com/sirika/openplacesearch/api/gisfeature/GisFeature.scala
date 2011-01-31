package com.sirika.openplacesearch.api.gisfeature

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

abstract case class GisFeature(protected[this] val featureGeography: FeatureGeographyProvider,
                            protected[this] val geonamesFeature: GeonamesFeatureProvider,
                            protected[this] val featureNames: FeatureNames) extends DistanceCalculator
  with GeonamesFeatureProvider
  with FeatureNameProvider
  with FeatureGeographyProvider
