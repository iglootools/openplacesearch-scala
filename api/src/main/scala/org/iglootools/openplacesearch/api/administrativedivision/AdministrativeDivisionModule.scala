package org.iglootools.openplacesearch.api.administrativedivision

import com.google.inject.{AbstractModule,Singleton}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final class AdministrativeDivisionModule extends AbstractModule {
  def configure: Unit = {
    bind(classOf[AdministrativeDivisionRepository]).to(classOf[InMemoryAdministrativeDivisionRepository]).in(classOf[Singleton])
    bind(classOf[CountryRepository]).to(classOf[InMemoryCountryRepository]).in(classOf[Singleton])
    bind(classOf[AlternateNamesLookup]).to(classOf[InMemoryAlternateNamesLookup]).in(classOf[Singleton])
  }
}