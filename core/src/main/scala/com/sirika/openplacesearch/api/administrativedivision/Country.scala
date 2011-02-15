package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.continent.Continent
import com.ibm.icu.util.{Currency, ULocale}
import com.sirika.openplacesearch.api.language.Language
import org.joda.time.DateTimeZone
import com.sirika.openplacesearch.api.feature.{FeatureNameProvider,LocalizedName, PopulationProvider, TimeZoneProvider}
import com.google.common.base.Objects

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class Country(val isoCountryCode: IsoCountryCode,
                         val continent: Continent,
                         val featureNameProvider: FeatureNameProvider,
                         val currency: Option[Currency] = None,
                         val fipsCountryCode: FipsCountryCode = FipsCountryCode(),
                         val countryAdministrativeInformation: CountryAdministrativeInformationProvider = CountryAdministrativeInformation(),  //private[this] : remove when deleting case class
                         val countryGeographicInformation: CountryGeographicInformationProvider = CountryGeographicInformation()) //private[this] : remove when deleting case class
                        (implicit val administrativeDivisionRepository: AdministrativeDivisionRepository)

  extends CountryAdministrativeInformationProvider
  with AdministrativeEntity
  with CurrencyProvider
  with CountryGeographicInformationProvider{

  //require(Option(name).exists {_.nonEmpty}, "name is required")
  require(continent != null, "continent is required")
  require(isoCountryCode != null, "isoCountryCode is required")
  require(fipsCountryCode != null, "fipsCountryCode is required")
  require(countryAdministrativeInformation != null, "countryAdministrativeInformation is required")

  // CountryAdministrativeInformationProvider
  def topLevelDomain: Option[String] = countryAdministrativeInformation.topLevelDomain
  def preferredLocales: List[ULocale] = countryAdministrativeInformation.preferredLocales
  def phonePrefix: Option[String] = countryAdministrativeInformation.phonePrefix
  def postalCodeRegex: Option[String] = countryAdministrativeInformation.postalCodeRegex
  def postalCodeMask: Option[String] = countryAdministrativeInformation.postalCodeMask

  // AdministrativeEntity
  def parentAdministrativeEntity = None
  def childAdministrativeDivisions: List[AdministrativeDivision] = administrativeDivisionRepository.findAllFirstOrderAdministrativeDivisions(this)

  // FeatureNameProvider
  def name: String = featureNameProvider.name
  def shortName(language: Language): String = featureNameProvider.shortName(language)
  def preferredName(language: Language): String = featureNameProvider.preferredName(language)
  def localizedNames: List[LocalizedName] = featureNameProvider.localizedNames
  def userFriendlyCode: Option[String] = Some(isoCountryCode.alpha2Code)

  // CountryGeographicInformationProvider
  def population: Option[Long] = countryGeographicInformation.population
  def areaInSquareKilometers: Option[Double] = countryGeographicInformation.areaInSquareKilometers

  override def hashCode(): Int = Objects.hashCode(isoCountryCode)

  override def equals(other: Any): Boolean = other match {
    case Country(`isoCountryCode`, _, _, _, _, _, _) => true
    case _ => false
  }
}