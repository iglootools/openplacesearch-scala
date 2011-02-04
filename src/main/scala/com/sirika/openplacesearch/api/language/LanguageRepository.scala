package com.sirika.openplacesearch.api.language

trait LanguageRepository {
  def findAll() : Seq[Language]
  def getByAlpha2Code(code : String) : Option[Language]
  def getByAlpha3Code(code : String) : Option[Language]
}