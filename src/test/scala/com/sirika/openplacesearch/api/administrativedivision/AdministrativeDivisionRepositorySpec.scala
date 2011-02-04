package com.sirika.openplacesearch.api.administrativedivision

import internal.{InMemoryAdministrativeDivisionRepository, InMemoryCountryRepository}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class AdministrativeDivisionRepositorySpec extends Spec with ShouldMatchers {
  val administrativeDivisionRepository: AdministrativeDivisionRepository= new InMemoryAdministrativeDivisionRepository
  val countryRepository: CountryRepository = new InMemoryCountryRepository

  val france = countryRepository.getByIsoAlpha3Code("FRA")

  describe("findAllFirstOrderAdministrativeDivisions") {
    it("of France should return 22 regions") {
      administrativeDivisionRepository.findAllFirstOrderAdministrativeDivisions(france).size should be === 22
    }
  }

  describe("getFirstOrderAdministrativeDivision") {
    it("should return Ile-de-france") {
      administrativeDivisionRepository.getFirstOrderAdministrativeDivision(france, "A8") should be === AdministrativeDivisions.France.ileDeFrance
    }

    it("should produce exception when code is unknown") {
      evaluating { administrativeDivisionRepository.getFirstOrderAdministrativeDivision(france, "ZZ") } should produce[NoSuchElementException]
    }
  }

  describe("getSecondOrderAdministrativeDivision") {
    it("should return Yvelines") {
      val ileDeFrance = administrativeDivisionRepository.getFirstOrderAdministrativeDivision(france, "A8")
      administrativeDivisionRepository.getSecondOrderAdministrativeDivision(france, ileDeFrance, "78") should be ===
        AdministrativeDivisions.France.IleDeFrance.yvelines
    }

    it("should produce exception when code is unknown") {
      evaluating { val ileDeFrance = administrativeDivisionRepository.getFirstOrderAdministrativeDivision(france, "A8")
      administrativeDivisionRepository.getSecondOrderAdministrativeDivision(france, ileDeFrance, "ZZ")  } should produce[NoSuchElementException]
    }
  }

  describe("findAllSecondOrderAdministrativeDivisions") {
    it("of France.IleDeFrance should return 8 departements") {
      val ileDeFrance = administrativeDivisionRepository.getFirstOrderAdministrativeDivision(france, "A8")
      administrativeDivisionRepository.findAllSecondOrderAdministrativeDivisions(france, ileDeFrance).size should be === 8
    }
  }
}

