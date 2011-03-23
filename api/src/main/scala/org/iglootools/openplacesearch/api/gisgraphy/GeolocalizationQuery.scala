package org.iglootools.openplacesearch.api.gisgraphy

import org.iglootools.openplacesearch.api.feature.LocationProvider
import resultparser.{ResultParser, GeolocalizationResultParser}
import org.iglootools.openplacesearch.api.administrativedivision.{AdministrativeDivisionRepository, CountryRepository}
import org.iglootools.openplacesearch.api.language.LanguageRepository
import org.iglootools.openplacesearch.api.geonames.StableIDMapper

final case class GeolocalizationQuery(val location: LocationProvider,
                                      val radiusInMeters: Int,
                                      val pagination: Pagination)
                                     (implicit protected[this] val gisgraphyServer: GisgraphyServer,
                                      implicit protected[this] val countryRepository: CountryRepository,
                                      implicit protected[this] val administrativeDivisionRepository: AdministrativeDivisionRepository,
                                      implicit protected[this] val languageRepository: LanguageRepository,
                                      implicit private[this] val stableIDMapper: StableIDMapper) extends GisgraphyQuery with UrlGenerator  {

  require(location != null, "location is required")
  require(radiusInMeters > 0, "radius must be a positive number")
  require(pagination != null, "pagination is required")

  def toUrl(baseUrl: String): String = {
    "%s/geoloc/geolocsearch?lat=%f&lng=%f&radius=%d&placetype=city&format=XML&__checkbox_indent=true&from=%d&to=%d".format(
      baseUrl,
      location.latitude,
      location.longitude,
      radiusInMeters,
      pagination.firstResult,
      pagination.endResult
    )
  }

  protected def resultParser: ResultParser = new GeolocalizationResultParser
}