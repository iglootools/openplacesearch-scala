package com.sirika.openplacesearch.api.language

trait LanguageRepository {
  def findAll() : Seq[Language]

  /**
   * @throws NoSuchElementException if no language matches the code
   */
  def getByAlpha2Code(code : String) : Language

  /**
   * @throws NoSuchElementException if no language matches the code
   */
  def getByAlpha3Code(code : String) : Language
}