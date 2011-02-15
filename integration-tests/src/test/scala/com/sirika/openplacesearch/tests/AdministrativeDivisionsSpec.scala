package com.sirika.openplacesearch.tests

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api._
import com.sirika.openplacesearch.api.ImplicitDependencyInjection._
import com.sirika.openplacesearch.api.administrativedivision.Places

@RunWith(classOf[JUnitRunner])
class AdministrativeDivisionsSpec extends Spec with ShouldMatchers {
  val countryRepository = ImplicitDependencyInjection.countryRepository

  val administrativeDivisionRepository = ImplicitDependencyInjection.administrativeDivisionRepository
  val france = countryRepository.getByIsoAlpha2Code("FR")
  val ileDeFrance = administrativeDivisionRepository.getFirstOrderAdministrativeDivision(france, "A8")

  describe("Country") {
    it("should find its child administrative divisions") {
      val regions = france.childAdministrativeDivisions
      regions.size should be > 0
    }
  }

  describe("AdministrativeDivision") {
    it("should find its child administrative divisions when it is adm1") {
      val departementsOfIleDeFrance = ileDeFrance.childAdministrativeDivisions
      departementsOfIleDeFrance.size should be > 0
    }

    it("should find no administrative divisions when it is adm2") {
      val yvelines = administrativeDivisionRepository.getSecondOrderAdministrativeDivision(france, ileDeFrance, "75")
      yvelines.childAdministrativeDivisions.size should be === 0
    }
  }

}