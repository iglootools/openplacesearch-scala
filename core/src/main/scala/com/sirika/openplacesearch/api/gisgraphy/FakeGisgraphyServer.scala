package com.sirika.openplacesearch.api.gisgraphy

import org.apache.http.client.HttpClient
import com.sirika.hchelpers.scala._
import grizzled.slf4j.Logging
import org.apache.http.client.methods.HttpGet
import org.apache.http.HttpResponse
import com.sirika.openplacesearch.api.language.LanguageRepository
import com.sirika.openplacesearch.api.gisgraphy.resultparser.ResultParser
import com.sirika.openplacesearch.api.administrativedivision.{Place, AdministrativeDivisionRepository, CountryRepository}
import com.sirika.openplacesearch.api.feature.LocationProvider
import com.sirika.openplacesearch.api.gisgraphy._
import com.sirika.openplacesearch.api
import com.sirika.commons.scala.io.InputSupliers
import com.google.common.io.InputSupplier
import java.io.InputStream

object FakeGisgraphyServer {
  def apply(resultForQuery: (GisgraphyQuery) => Option[InputSupplier[InputStream]] = { q => None }): FakeGisgraphyServer = {
    import api.ImplicitDependencyInjection._
    new FakeGisgraphyServer(resultForQuery)
  }
}

final class FakeGisgraphyServer(private[this] val resultForQuery: (GisgraphyQuery) => Option[InputSupplier[InputStream]])
                               (implicit private[this] val countryRepository: CountryRepository,
                                implicit private[this] val administrativeDivisionRepository: AdministrativeDivisionRepository,
                                implicit private[this] val languageRepository: LanguageRepository)
  extends GisgraphyServer with Logging {
  implicit val gisgraphyServer: GisgraphyServer = this

  def execute[T](query: GisgraphyQuery, resultParser: ResultParser): List[Place] = {
    val inputSupplierOption = mapQueryToInputSupplier(query)
    inputSupplierOption match {
      case Some(inputSupplier) =>
        val places = InputSupliers.doWithInputStream(inputSupplier) { is =>
          resultParser.toPlaces(is)
        }
        places.slice(start = query.pagination.firstResult-1, end = query.pagination.endResult)
      case None => List()
    }
  }

  def mapQueryToInputSupplier(query: GisgraphyQuery): Option[InputSupplier[InputStream]] = {
    resultForQuery(query)
  }

  def newGeolocalizationQuery(location: LocationProvider, radiusInMeters: Int, pagination: Pagination): GisgraphyQuery = new GeolocalizationQuery(location, radiusInMeters, pagination)
  def newFullTextQuery(query: String, pagination: Pagination): GisgraphyQuery = new FullTextQuery(query, pagination)
}