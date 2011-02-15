package com.sirika.openplacesearch.api.gisgraphy

import com.sirika.openplacesearch.api.feature.LocationProvider
import resultparser.{ResultParser, GeolocalizationResultParser}
import com.sirika.openplacesearch.api.administrativedivision.{AdministrativeDivisionRepository, CountryRepository}
import com.sirika.openplacesearch.api.language.LanguageRepository

protected[gisgraphy] final class GeolocalizationQuery(private[this] val location: LocationProvider,
                                 private[this] val radiusInMeters: Int,
                                 private[this] val pagination: Pagination)
                                (implicit protected[this] val gisgraphyServer: GisgraphyServer,
                                 implicit protected[this] val countryRepository: CountryRepository,
                                 implicit protected[this] val administrativeDivisionRepository: AdministrativeDivisionRepository,
                                 implicit protected[this] val languageRepository: LanguageRepository) extends GisgraphyQuery with UrlGenerator  {

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