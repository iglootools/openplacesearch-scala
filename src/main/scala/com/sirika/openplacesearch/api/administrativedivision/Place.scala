package com.sirika.openplacesearch.api.administrativedivision

import org.joda.time.DateTimeZone
import com.vividsolutions.jts.geom.Point
import com.sirika.openplacesearch.api.commons.{ParentAdministrativeEntityProvider, FeatureGeographyProvider, LocationProvider}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

abstract case class Place(
  protected[this] val featureGeographyProvider:FeatureGeographyProvider,
  protected[this] val parentAdministrativeEntityProvider:ParentAdministrativeEntityProvider)
  extends FeatureGeographyProvider
  with AdministrativeEntity {

  // FeatureGeographyProvider
  def timeZone: Option[DateTimeZone] = featureGeographyProvider.timeZone
  def elevationInMeters: Option[Long] = featureGeographyProvider.elevationInMeters
  def gTopo30ElevationInMeters: Option[Long] = featureGeographyProvider.gTopo30ElevationInMeters
  def population: Option[Long] = featureGeographyProvider.population
  def location: Point = featureGeographyProvider.location

  // ParentAdministrativeEntity
  def parentAdministrativeEntity: Option[AdministrativeEntity] = null
}