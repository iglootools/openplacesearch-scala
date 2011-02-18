package com.sirika.openplacesearch.api.administrativedivision

import org.joda.time.DateTimeZone
import com.vividsolutions.jts.geom.Point
import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.feature._
import com.ibm.icu.util.Currency
import com.google.common.base.Objects

object Place {
  def apply(parentAdministrativeEntityProvider:ParentAdministrativeEntityProvider,
            featureGeographyProvider: FeatureGeographyProvider,
            featureNameProvider: FeatureNameProvider,
            stableIdProvider: StableIdProvider): Place = {
    new Place(
      parentAdministrativeEntityProvider = parentAdministrativeEntityProvider,
      featureGeographyProvider = featureGeographyProvider,
      featureNameProvider = featureNameProvider,
      stableIdProvider = stableIdProvider)
  }
}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final class Place(protected[this] val parentAdministrativeEntityProvider:ParentAdministrativeEntityProvider,
                  protected[this] val featureGeographyProvider: FeatureGeographyProvider,
                  protected[this] val featureNameProvider: FeatureNameProvider,
                  protected[this] val stableIdProvider: StableIdProvider)
  extends FeatureGeographyProvider
  with AdministrativeEntity
  with StableIdProvider
  with DistanceCalculator
  with Ordered[Place]
  with CurrencyProvider {

  // FeatureGeographyProvider
  def timeZone: Option[DateTimeZone] = featureGeographyProvider.timeZone
  def elevationInMeters: Option[Long] = featureGeographyProvider.elevationInMeters
  def gTopo30ElevationInMeters: Option[Long] = featureGeographyProvider.gTopo30ElevationInMeters
  def population: Option[Long] = featureGeographyProvider.population
  def location: Point = featureGeographyProvider.location

  // ParentAdministrativeEntity
  def parentAdministrativeEntity: Option[AdministrativeEntity] = parentAdministrativeEntityProvider.parentAdministrativeEntity
  def childAdministrativeDivisions: List[AdministrativeDivision] = List()


  // StableIdProvider
  def stableId: String = stableIdProvider.stableId

  // FeatureNameProvider
  def shortName(language: Language): String = featureNameProvider.shortName(language)
  def preferredName(language: Language): String = featureNameProvider.preferredName(language)
  def localizedNames: List[LocalizedName] = featureNameProvider.localizedNames
  def userFriendlyCode: Option[String] = featureNameProvider.userFriendlyCode
  def name: String = featureNameProvider.name

  // Ordered
  def compare(that: Place): Int = name.compare(that.name)

  // CurrencyProvider
  def currency: Option[Currency] = country.currency

  override def hashCode(): Int = Objects.hashCode(stableId)

  override def equals(other: Any): Boolean = other match {
    case p: Place if Objects.equal(this.stableId, p.stableId) => true
    case _ => false
  }

  override def toString(): String = Objects.toStringHelper(this)
    .add("name", name)
    .add("stableId", stableId)
    .toString
}