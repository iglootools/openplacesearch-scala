package com.sirika.openplacesearch.api.gisgraphy

import java.io.InputStream
import com.sirika.openplacesearch.api.feature.LocationProvider
import resultparser.ResultParser
import com.sirika.openplacesearch.api.administrativedivision.Place
;

trait GisgraphyServer {
  /**
   * @throws Exception on failure
   */
  def execute[T](urlGenerator: UrlGenerator, resultParser: ResultParser) : List[Place]

  def newFullTextQuery(query: String, pagination: Pagination = Pagination(firstResult=1,numberOfResults=10)): GisgraphyQuery
  def newGeolocalizationQuery(location: LocationProvider, radiusInMeters: Int, pagination: Pagination = Pagination(firstResult=1,numberOfResults=10)): GisgraphyQuery
}