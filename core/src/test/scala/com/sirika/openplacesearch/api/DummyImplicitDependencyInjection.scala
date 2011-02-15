package com.sirika.openplacesearch.api

import administrativedivision.{AdministrativeDivisionRepository, CountryRepository}
import language._
;

object DummyImplicitDependencyInjection {
  implicit val countryRepository: CountryRepository = new CountryRepository {
    def findAll(): List[Country] = throw new RuntimeException("not implemented")
    def getByFipsCode(code: String): Country = throw new RuntimeException("not implemented")
    def getByIsoAlpha3Code(code: String): Country = throw new RuntimeException("not implemented")
    def getByIsoAlpha2Code(code: String): Country = throw new RuntimeException("not implemented")
  }

  implicit val administrativeDivisionRepository: AdministrativeDivisionRepository = new AdministrativeDivisionRepository {
    def findAllSecondOrderAdministrativeDivisions(country: Country, firstOrderAdministrativeDivision: AdministrativeDivision): List[AdministrativeDivision] = throw new RuntimeException("not implemented")
    def getSecondOrderAdministrativeDivision(country: Country, firstOrderAdministrativeDivision: AdministrativeDivision, code: String): AdministrativeDivision = throw new RuntimeException("not implemented")
    def findAllFirstOrderAdministrativeDivisions(country: Country): List[AdministrativeDivision] = throw new RuntimeException("not implemented")
    def getFirstOrderAdministrativeDivision(country: Country, code: String): AdministrativeDivision = throw new RuntimeException("not implemented")
  }

  implicit val languageRepository: LanguageRepository = new LanguageRepository {
    def maybeGetByAlpha3Code(code: String): Option[Language] = throw new RuntimeException("not implemented")
    def maybeGetByAlpha2Code(code: String): Option[Language] = throw new RuntimeException("not implemented")
    def getByAlpha3Code(code: String): Language = throw new RuntimeException("not implemented")
    def getByAlpha2Code(code: String): Language = throw new RuntimeException("not implemented")
    def findAll(): Seq[Language] = throw new RuntimeException("not implemented")
  }
}