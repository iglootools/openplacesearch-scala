package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.continent.Continents
import com.ibm.icu.util.{ULocale, Currency}
import com.sirika.openplacesearch.api.DummyImplicitDependencyInjection._

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object Countries {
  def unitedStates: Country = Country(
    isoCountryCode=IsoCountryCode(alpha3Code="USA",alpha2Code="US", numeric=840),
    continent=Continents.northAmerica,
    featureNameProvider= SimpleFeatureNameProvider(defaultName = "United States", parentAdministrativeEntity=None),
    currency=Some(Currency.getInstance("USD")),
    fipsCountryCode=FipsCountryCode(fipsCode=Some("US")),
    countryAdministrativeInformation=
      CountryAdministrativeInformation(
        preferredLocales=List(new ULocale("en_US"), new ULocale("es_US"), new ULocale("haw"), new ULocale("fr")),
        topLevelDomain=Some(".us"),
        phonePrefix=Some("1"),
        postalCodeRegex=Some("""^(\d{9})$"""),
        postalCodeMask=Some("#####-####")),
    countryGeographicInformation=CountryGeographicInformation(population = Some(303824000L), areaInSquareKilometers = Some(9629091))
  )

  def usOutlyingIslands: Country = Country(
    isoCountryCode=IsoCountryCode(alpha3Code="UMI",alpha2Code="UM", numeric=581),
    continent=Continents.oceania,
    featureNameProvider= SimpleFeatureNameProvider(defaultName = "United States Minor Outlying Islands", parentAdministrativeEntity=None),
    currency=Some(Currency.getInstance("USD")),
    fipsCountryCode=FipsCountryCode(fipsCode=None),
    countryAdministrativeInformation=
      CountryAdministrativeInformation(
        preferredLocales=List(),
        topLevelDomain=Some(".am"),
        phonePrefix=None,
        postalCodeRegex=None,
        postalCodeMask=None),
    countryGeographicInformation=CountryGeographicInformation(population = None, areaInSquareKilometers = None)
  )

  def france: Country = Country(
    isoCountryCode=IsoCountryCode(alpha3Code="FRA",alpha2Code="FR", numeric=250),
    continent=Continents.europe,
    featureNameProvider= SimpleFeatureNameProvider(defaultName = "France", parentAdministrativeEntity=None),
    currency=Some(Currency.getInstance("EUR")),
    fipsCountryCode=FipsCountryCode(fipsCode=Some("FR")),
    countryAdministrativeInformation=
      CountryAdministrativeInformation(
        preferredLocales=List(new ULocale("fr_FR"), new ULocale("frp"), new ULocale("br"), new ULocale("co"), new ULocale("ca"), new ULocale("eu"), new ULocale("oc")),
        topLevelDomain=Some(".fr"),
        phonePrefix=Some("33"),
        postalCodeRegex=Some("""^(\d{5})$"""),
        postalCodeMask=Some("#####")),
    countryGeographicInformation=CountryGeographicInformation(population = Some(64768389L), areaInSquareKilometers = Some(547030))
  )

  def antarctica: Country = Country(
    isoCountryCode=IsoCountryCode(alpha3Code="ATA",alpha2Code="AQ", numeric=10),
    continent=Continents.antarctica,
    featureNameProvider= SimpleFeatureNameProvider(defaultName = "Antarctica", parentAdministrativeEntity=None),
    currency=None,
    fipsCountryCode=FipsCountryCode(fipsCode=Some("AY")),
    countryAdministrativeInformation=
      CountryAdministrativeInformation(
        preferredLocales=List(),
        topLevelDomain=Some(".aq"),
        phonePrefix=None,
        postalCodeRegex=None,
        postalCodeMask=None),
    countryGeographicInformation=CountryGeographicInformation(population = Some(0L), areaInSquareKilometers = Some(1.40E+007))
  )
}
