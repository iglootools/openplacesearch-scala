package org.iglootools.openplacesearch.api.administrativedivision

import org.iglootools.openplacesearch.api.continent.Continent
import com.ibm.icu.util.{Currency, ULocale}
import org.iglootools.openplacesearch.api.language.Language
import org.iglootools.openplacesearch.api.feature.{FeatureNameProvider,LocalizedName}
import com.google.common.base.Objects

object Country {
  def apply(isoCountryCode: IsoCountryCode,
            continent: Continent,
            featureNameProvider: FeatureNameProvider,
            currency: Option[Currency] = None,
            fipsCountryCode: FipsCountryCode = FipsCountryCode(),
            countryAdministrativeInformation: CountryAdministrativeInformationProvider = CountryAdministrativeInformation(),
            countryGeographicInformation: CountryGeographicInformationProvider = CountryGeographicInformation())
           (implicit administrativeDivisionRepository: AdministrativeDivisionRepository): Country = {
    new Country(
      isoCountryCode=isoCountryCode,
      continent=continent,
      featureNameProvider=featureNameProvider,
      currency=currency,
      fipsCountryCode=fipsCountryCode,
      countryAdministrativeInformation=countryAdministrativeInformation,
      countryGeographicInformation=countryGeographicInformation)
  }

}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final class Country(val isoCountryCode: IsoCountryCode,
                    val continent: Continent,
                    val featureNameProvider: FeatureNameProvider,
                    val currency: Option[Currency] = None,
                    val fipsCountryCode: FipsCountryCode = FipsCountryCode(),
                    private[this] val countryAdministrativeInformation: CountryAdministrativeInformationProvider,
                    private[this] val countryGeographicInformation: CountryGeographicInformationProvider)
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
    case c: Country if Objects.equal(this.isoCountryCode, c.isoCountryCode) => true
    case _ => false
  }

  override def toString(): String = Objects.toStringHelper(this)
    .add("name", name)
    .add("alpha2Code", isoCountryCode.alpha2Code)
    .add("alpha3Code", isoCountryCode.alpha3Code)
    .toString
}