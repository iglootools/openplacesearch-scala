package com.sirika.openplacesearch.api.administrativedivision

import com.google.inject.AbstractModule

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final class AdministrativeDivisionModule extends AbstractModule {
  def configure: Unit = {
    bind(classOf[AdministrativeDivisionRepository]).to(classOf[InMemoryAdministrativeDivisionRepository])
    bind(classOf[CountryRepository]).to(classOf[InMemoryCountryRepository])
    bind(classOf[AlternateNamesLookup]).to(classOf[InMemoryAlternateNamesLookup])
  }
}