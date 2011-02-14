package com.sirika.openplacesearch.api.gisgraphy.http

import java.io.InputStream
import org.apache.http.client.HttpClient
import com.sirika.hchelpers.scala.Implicits._
import grizzled.slf4j.Logging
import org.apache.http.client.methods.HttpGet
import org.apache.http.HttpResponse
import com.sirika.hchelpers.scala.{HttpErrorHandler, SimpleHttpClient}
import com.sirika.openplacesearch.api.language.LanguageRepository
import com.sirika.openplacesearch.api.gisgraphy.resultparser.ResultParser
import com.sirika.openplacesearch.api.administrativedivision.{Place, AdministrativeDivisionRepository, CountryRepository}
import com.sirika.openplacesearch.api.feature.LocationProvider
import com.sirika.openplacesearch.api.gisgraphy._

final class HttpGisgraphyServer(private[this] val baseUrl: String,
                                private[this] val httpClient: HttpClient = SimpleHttpClient())
                               (implicit private[this] val countryRepository: CountryRepository,
                                implicit private[this] val administrativeDivisionRepository: AdministrativeDivisionRepository,
                                implicit private[this] val languageRepository: LanguageRepository)
  extends GisgraphyServer with Logging {
  require(Option(baseUrl) exists (_.nonEmpty), "baseUrl is required")
  require(httpClient != null, "httpClient is required")
  implicit val gisgraphyServer: GisgraphyServer = this

  info("Created HttpGisgraphyServer with baseUrl: %s".format(baseUrl))

  def execute[T](urlGenerator: UrlGenerator, resultParser: ResultParser): List[Place] = {
    try {
      val result = httpClient.doWithResponse(new HttpGet(urlGenerator.toUrl(baseUrl)),
        doOnSuccess = {r: HttpResponse =>
          assume(r.getEntity != null, "the entity cannot be null")
          resultParser.toPlaces(r.getEntity.getContent)
        })
      result match {
        case Right(r) => r
        case Left(e) => throw new GisgraphyException(message="Received an unexpected HTTP exception. This  may mean that gisgraphy HTTP server is wrongly configured", cause=e)
      }
    } catch {
      case e => throw new GisgraphyException(message="System failure. The connection either crashed or a timeout happened", cause=e);
    }

  }

  def newGeolocalizationQuery(location: LocationProvider, radiusInMeters: Int, pagination: Pagination): GisgraphyQuery = new GeolocalizationQuery(location, radiusInMeters, pagination)
  def newFullTextQuery(query: String, pagination: Pagination): GisgraphyQuery = new FullTextQuery(query, pagination)
}