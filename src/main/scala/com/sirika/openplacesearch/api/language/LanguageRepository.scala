package com.sirika.openplacesearch.api.language

trait LanguageRepository {
    def findAllLanguages() : Seq[Language]
    def findByAlpha2Code(code : String) : Option[Language]
    def findByAlpha3Code(code : String) : Option[Language]
}