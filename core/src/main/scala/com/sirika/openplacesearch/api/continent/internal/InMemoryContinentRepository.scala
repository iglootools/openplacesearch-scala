package com.sirika.openplacesearch.api.continent.internal

import com.sirika.openplacesearch.api.continent.Continent
import scala.collection.immutable.List
import com.sirika.openplacesearch.api.continent.ContinentRepository

@com.google.inject.Singleton()
protected[continent] final class InMemoryContinentRepository extends ContinentRepository {
  private lazy val continents = List(
    Continent("AF", "Africa"),
    Continent("AS", "Asia"),
    Continent("EU", "Europe"),
    Continent("NA", "North America"),
    Continent("OC", "Oceania"),
    Continent("SA", "South America"),
    Continent("AN", "Antarctica"))
  private lazy val continentsLookupTable = Map(continents map {c => (c.geonamesCode, c)} : _*)

  def findAll(): List[Continent] = { continents }

  def getByGeonamesCode(code: String): Continent = continentsLookupTable.get(code).get

}