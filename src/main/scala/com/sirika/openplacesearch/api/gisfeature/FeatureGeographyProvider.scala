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

  def location: Point
  def population: Option[Long]
  def gTopo30ElevationInMeters: Option[Long]
  def elevationInMeters: Option[Long]
  def timeZone: Option[DateTimeZone]
}