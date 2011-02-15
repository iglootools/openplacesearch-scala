package com.sirika.openplacesearch

import api.administrativedivision.{AdministrativeDivisionModule, AdministrativeDivisionRepository, CountryRepository}
import api.continent.ContinentModule
import api.language.{LanguageModule, LanguageRepository}

import com.google.inject.Guice

package object api {
  val ApplicationContext = Guice.createInjector(new AdministrativeDivisionModule(), new ContinentModule(), new LanguageModule())

  object Implicits {
    implicit val administrativeDivisionRepository = api.ApplicationContext.getInstance(classOf[AdministrativeDivisionRepository])
    implicit val countryRepository = api.ApplicationContext.getInstance(classOf[CountryRepository])
    implicit val languageRepository = api.ApplicationContext.getInstance(classOf[LanguageRepository])
  }
}


