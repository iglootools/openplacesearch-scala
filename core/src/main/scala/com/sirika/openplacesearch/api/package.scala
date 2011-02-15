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

  // package: administrativedivision
  type AdministrativeDivision = api.administrativedivision.AdministrativeDivision
  type AdministrativeEntity = api.administrativedivision.AdministrativeEntity
  type Country = api.administrativedivision.Country
  type CountryAdministrativeInformationProvider = api.administrativedivision.CountryAdministrativeInformationProvider
  type CountryGeographicInformationProvider = api.administrativedivision.CountryGeographicInformationProvider
  type CountryProvider = api.administrativedivision.CountryProvider
  type CurrencyProvider = api.administrativedivision.CurrencyProvider
  type FipsCountryCode = api.administrativedivision.FipsCountryCode
  type IsoCountryCode = api.administrativedivision.IsoCountryCode
  type PhonePrefixProvider = api.administrativedivision.PhonePrefixProvider
  type Place = api.administrativedivision.Place
  type PostalCodeFormatProvider = api.administrativedivision.PostalCodeFormatProvider
  type PreferredLocalesProvider = api.administrativedivision.PreferredLocalesProvider
   type TopLevelDomainProvider = api.administrativedivision.TopLevelDomainProvider
//  type CountryRepository = api.administrativedivision.CountryRepository
//  type CountryRepository = api.administrativedivision.CountryRepository
//  type CountryRepository = api.administrativedivision.CountryRepository
//  type CountryRepository = api.administrativedivision.CountryRepository
//  type CountryRepository = api.administrativedivision.CountryRepository
//  type CountryRepository = api.administrativedivision.CountryRepository
//  type CountryRepository = api.administrativedivision.CountryRepository
//  type CountryRepository = api.administrativedivision.CountryRepository

}


