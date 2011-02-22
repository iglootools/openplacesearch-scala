package org.iglootools.openplacesearch.api.continent

trait ContinentRepository {
  def findAll() : List[Continent]

  /**
   * @throws NoSuchElementException if no continent matching the code is found
   */
  def getByGeonamesCode(code: String) : Continent
}