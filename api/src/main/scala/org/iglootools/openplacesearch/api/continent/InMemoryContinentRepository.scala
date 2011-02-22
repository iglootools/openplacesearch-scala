package org.iglootools.openplacesearch.api.continent

import scala.collection.immutable.List

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