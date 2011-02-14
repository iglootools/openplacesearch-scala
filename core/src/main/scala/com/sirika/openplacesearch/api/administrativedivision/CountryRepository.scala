package com.sirika.openplacesearch.api.administrativedivision

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait CountryRepository {
  /**
   * @throws NoSuchElementException if no country matches the given code
   */
  def getByIsoAlpha2Code(code: String): Country

  /**
   * @throws NoSuchElementException if no country matches the given code
   */
  def getByIsoAlpha3Code(code: String): Country

  /**
   * @throws NoSuchElementException if no country matches the given code
   */
  def getByFipsCode(code: String): Country


  def findAll(): List[Country]

}