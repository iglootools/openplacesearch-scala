package org.iglootools.openplacesearch.api.gisgraphy

import org.apache.http.client.HttpClient
import org.iglootools.hchelpers.scala._
import grizzled.slf4j.Logging
import org.apache.http.client.methods.HttpGet
import org.apache.http.HttpResponse
import org.iglootools.openplacesearch.api.language.LanguageRepository
import org.iglootools.openplacesearch.api.gisgraphy.resultparser.ResultParser
import org.iglootools.openplacesearch.api.administrativedivision.{Place, AdministrativeDivisionRepository, CountryRepository}
import org.iglootools.openplacesearch.api.feature.LocationProvider
import org.iglootools.openplacesearch.api.gisgraphy._
import org.iglootools.openplacesearch.api
import api.geonames.StableIDMapper

object HttpGisgraphyServer {
  def apply(baseUrl: String, httpClient: HttpClient=SimpleHttpClient(maxTotalNumberOfConnections=100, defaultMaxNumberOfConnectionsPerRoute=100)): HttpGisgraphyServer = {
    import api.ImplicitDependencyInjection._
    new HttpGisgraphyServer(baseUrl, httpClient)
  }
}

final class HttpGisgraphyServer(private[this] val baseUrl: String,
                                private[this] val httpClient: HttpClient)
                               (implicit private[this] val countryRepository: CountryRepository,
                                implicit private[this] val administrativeDivisionRepository: AdministrativeDivisionRepository,
                                implicit private[this] val languageRepository: LanguageRepository,
                                implicit protected[this] val stableIDMapper: StableIDMapper)
  extends GisgraphyServer with Logging {
  require(Option(baseUrl) exists (_.nonEmpty), "baseUrl is required")
  require(httpClient != null, "httpClient is required")
  implicit val gisgraphyServer: GisgraphyServer = this

  info("Created HttpGisgraphyServer with baseUrl: %s".format(baseUrl))

  def execute[T](query: GisgraphyQuery, resultParser: ResultParser): List[Place] = {
    try {
      val result = httpClient.doWithResponse(new HttpGet(query.toUrl(baseUrl)),
        onSuccess = {r: HttpResponse =>
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