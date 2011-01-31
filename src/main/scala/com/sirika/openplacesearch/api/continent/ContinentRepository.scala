package com.sirika.openplacesearch.api.continent

trait ContinentRepository {
  def findAll() : List[Continent]
  def findByGeonamesCode(code: String) : Option[Continent]
}