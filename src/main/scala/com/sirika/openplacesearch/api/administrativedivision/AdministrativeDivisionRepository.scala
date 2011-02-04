package com.sirika.openplacesearch.api.administrativedivision

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait AdministrativeDivisionRepository {
  /**
   * Finds the first order administrative division
   * <p> For the US, this is the State
   * </p>
   *
   * @throws NoSuchElementException if no adm matches the given code
   */
  def getFirstOrderAdministrativeDivision(country: Country, code: String): AdministrativeDivision
  def findAllFirstOrderAdministrativeDivisions(country: Country): List[AdministrativeDivision]

  /**
   * Finds the second order administrative division
   * <p> For the US, this is the county
   * </p>
   *
   * @throws NoSuchElementException if no adm matches the given code
   */
  def getSecondOrderAdministrativeDivision(country: Country, firstOrderAdministrativeDivision: AdministrativeDivision, code: String): AdministrativeDivision
  def findAllSecondOrderAdministrativeDivisions(country: Country, firstOrderAdministrativeDivision: AdministrativeDivision): List[AdministrativeDivision]
}