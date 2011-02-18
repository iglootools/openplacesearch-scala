package com.sirika.openplacesearch.api.feature

import org.joda.time.DateTimeZone
import com.sirika.openplacesearch.api
import com.vividsolutions.jts.geom.{Coordinate, Point}
import com.sirika.commons.scala.jts.Wgs84


/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class FeatureGeography(
  val location: Point,
  val population: Option[Long],
  val gTopo30ElevationInMeters: Option[Long],
  val elevationInMeters: Option[Long],
  val timeZone: Option[DateTimeZone]) extends FeatureGeographyProvider {

  require(Option(location) exists {_.coveredBy(Wgs84.ValidCoordinateBounds)}, "The location must be on earth (within the bounds of WGS84)")
  require(population != null, "population must be a non-null Option")
  require(gTopo30ElevationInMeters != null, "gTopo30ElevationInMeters must be a non-null Option")
  require(elevationInMeters != null, "elevationInMeters must be a non-null Option")
  require(timeZone != null, "timeZone must be a non-null Option")

}