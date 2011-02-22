package org.iglootools.openplacesearch.api.administrativedivision

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.openplacesearch.api

@RunWith(classOf[JUnitRunner])
class AdministrativeDivisionRepositorySpec extends Spec with ShouldMatchers {
  val administrativeDivisionRepository = api.ImplicitDependencyInjection.administrativeDivisionRepository
  val countryRepository = api.ImplicitDependencyInjection.countryRepository

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
      yvelines should be ===
        AdministrativeDivisions.France.IleDeFrance.yvelines
    }

    it("should produce exception when code is unknown") {
      evaluating { administrativeDivisionRepository.getSecondOrderAdministrativeDivision(france, ileDeFrance, "ZZ")  } should produce[NoSuchElementException]
    }
  }

  describe("findAllSecondOrderAdministrativeDivisions") {
    it("of France.IleDeFrance should return 8 departements") {
      administrativeDivisionRepository.findAllSecondOrderAdministrativeDivisions(france, ileDeFrance).size should be === 8
    }
  }

  describe("first order administrative divisions") {
    it("should have localized names") {
      ileDeFrance.localizedNames.size should be >= 5
    }
  }

    describe("second order administrative divisions") {
    it("should have localized names") {
      yvelines.localizedNames.size should be >= 5
    }
  }

  def yvelines = administrativeDivisionRepository.getSecondOrderAdministrativeDivision(france, ileDeFrance, "78")
  def france = countryRepository.getByIsoAlpha3Code("FRA")
  def ileDeFrance = administrativeDivisionRepository.getFirstOrderAdministrativeDivision(france, "A8")
}

