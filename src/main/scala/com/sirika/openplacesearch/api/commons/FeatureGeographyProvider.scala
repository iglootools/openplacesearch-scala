package com.sirika.openplacesearch.api.commons

import org.joda.time.DateTimeZone
import com.vividsolutions.jts.geom.Point
import com.sirika.openplacesearch.api.commons.{LocationProvider, TimeZoneProvider, PopulationProvider}

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