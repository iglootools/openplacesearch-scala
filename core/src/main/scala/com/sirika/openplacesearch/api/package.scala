package com.sirika.openplacesearch

import api.administrativedivision.{AdministrativeDivisionModule, AdministrativeDivisionRepository, CountryRepository}
import api.continent.ContinentModule
import api.language.{LanguageModule, LanguageRepository}

import com.google.inject.Guice

package object api {
  lazy val DefaultApplicationContext = Guice.createInjector(new AdministrativeDivisionModule(), new ContinentModule(), new LanguageModule())

  object ImplicitDependencyInjection {
    implicit val administrativeDivisionRepository = api.DefaultApplicationContext.getInstance(classOf[AdministrativeDivisionRepository])
    implicit val countryRepository = api.DefaultApplicationContext.getInstance(classOf[CountryRepository])
    implicit val languageRepository = api.DefaultApplicationContext.getInstance(classOf[LanguageRepository])
  }

  // administrativedivision
  type AdministrativeDivision = api.administrativedivision.AdministrativeDivision
  type Country = api.administrativedivision.Country
  type Place = api.administrativedivision.Place

  type FipsCountryCode = api.administrativedivision.FipsCountryCode
  type IsoCountryCode = api.administrativedivision.IsoCountryCode

  type AdministrativeEntity = api.administrativedivision.AdministrativeEntity
  type CountryAdministrativeInformationProvider = api.administrativedivision.CountryAdministrativeInformationProvider
  type CountryGeographicInformationProvider = api.administrativedivision.CountryGeographicInformationProvider
  type CountryProvider = api.administrativedivision.CountryProvider
  type CurrencyProvider = api.administrativedivision.CurrencyProvider
  type PhonePrefixProvider = api.administrativedivision.PhonePrefixProvider
  type PostalCodeFormatProvider = api.administrativedivision.PostalCodeFormatProvider
  type PreferredLocalesProvider = api.administrativedivision.PreferredLocalesProvider
  type TopLevelDomainProvider = api.administrativedivision.TopLevelDomainProvider

  // continent
  type Continent = api.continent.Continent

  // feature
  type LocalizedName = api.feature.LocalizedName
  type NamePart = api.feature.NamePart

  type DistanceCalculator = api.feature.DistanceCalculator
  type ElevationProvider = api.feature.ElevationProvider
  type FeatureGeographyProvider = api.feature.FeatureGeographyProvider
  type FeatureNameProvider = api.feature.FeatureNameProvider
  type LocationProvider = api.feature.LocationProvider
  type ParentAdministrativeEntityProvider = api.feature.ParentAdministrativeEntityProvider
  type PopulationProvider = api.feature.PopulationProvider
  type StableIdProvider = api.feature.StableIdProvider
  type TimeZoneProvider = api.feature.TimeZoneProvider


  // formatting
  type NameFormatter = api.formatting.NameFormatter
  val Formatters = api.formatting.Formatters

  // gisgraphy
  type GisgraphyServer = api.gisgraphy.GisgraphyServer
  type GisgraphyQuery = api.gisgraphy.GisgraphyQuery
  type Pagination = api.gisgraphy.Pagination
  val Pagination = api.gisgraphy.Pagination
  type GisgraphyException = api.gisgraphy.GisgraphyException

  val HttpGisgraphyServer = api.gisgraphy.http.HttpGisgraphyServer

  // language
  type Language = api.language.Language
}


