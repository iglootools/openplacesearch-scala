package com.sirika.openplacesearch.api.gisfeature

import org.joda.time.DateTimeZone
import com.vividsolutions.jts.geom.Point

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait FeatureGeographyProvider extends LocationProvider
  with ElevationProvider
  with PopulationProvider
  with TimeZoneProvider {

  self: {
    def featureGeography: FeatureGeographyProvider
  }  =>

  def location: Point = featureGeography.location

  def population: Option[Long] = featureGeography.population

  def gTopo30ElevationInMeters: Option[Long] = featureGeography.gTopo30ElevationInMeters

  def elevationInMeters: Option[Long] = featureGeography.elevationInMeters

  def timeZone: Option[DateTimeZone] = featureGeography.timeZone
}