package org.iglootools.openplacesearch.api.gisgraphy

import org.iglootools.openplacesearch.api.feature.LocationProvider
import resultparser.ResultParser
import org.iglootools.openplacesearch.api.administrativedivision.Place

trait GisgraphyServer {

  def stableIDMapper: StableIDMapper

  /**
   * @throws Exception on failure
   */
  def execute[T](query: GisgraphyQuery, resultParser: ResultParser) : List[Place]

  def getById(id: String): Place = {
    val query = stableIDMapper.originalID(id).getOrElse(id)
    newFullTextQuery(query=query, pagination=Pagination(firstResult=1,numberOfResults=1)).execute.head
  }
  def newFullTextQuery(query: String, pagination: Pagination = Pagination(firstResult=1,numberOfResults=10)): GisgraphyQuery
  def newGeolocalizationQuery(location: LocationProvider, radiusInMeters: Int, pagination: Pagination = Pagination(firstResult=1,numberOfResults=10)): GisgraphyQuery
}