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
}


