package com.sirika.openplacesearch.api.administrativedivision

import com.google.inject.AbstractModule
import internal.{InMemoryCountryRepository, InMemoryAdministrativeDivisionRepository}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

class AdministrativeDivisionModule extends AbstractModule {
  def configure: Unit = {
    bind(classOf[AdministrativeDivisionRepository]).to(classOf[InMemoryAdministrativeDivisionRepository])
    bind(classOf[CountryRepository]).to(classOf[InMemoryCountryRepository])
  }
}