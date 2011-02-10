package com.sirika.openplacesearch

import api.administrativedivision.{AdministrativeDivisionModule, AdministrativeDivisionRepository, CountryRepository}
import api.continent.ContinentModule
import api.language.{LanguageModule, LanguageRepository}
import com.vividsolutions.jts.geom.{PrecisionModel, GeometryFactory, Envelope, Geometry}
import com.google.inject.Guice

package object api {
  private[api] val WGS84_SRID = 4326
  private[api] val WGS84_ENVELOPE = new Envelope(-180.0, 180.0, -90.0, 90.0)
  private[api] val Precision: PrecisionModel = new PrecisionModel(PrecisionModel.FLOATING)
  protected[api] val GeometryFactory: GeometryFactory = new GeometryFactory(Precision, WGS84_SRID)
  protected[api] val ValidCoordinateBounds: Geometry = api.GeometryFactory.toGeometry(WGS84_ENVELOPE);

  val ApplicationContext = Guice.createInjector(new AdministrativeDivisionModule(), new ContinentModule(), new LanguageModule())

  object Implicits {
    implicit val administrativeDivisionRepository = api.ApplicationContext.getInstance(classOf[AdministrativeDivisionRepository])
    implicit val countryRepository = api.ApplicationContext.getInstance(classOf[CountryRepository])
    implicit val languageRepository = api.ApplicationContext.getInstance(classOf[LanguageRepository])
  }
}


