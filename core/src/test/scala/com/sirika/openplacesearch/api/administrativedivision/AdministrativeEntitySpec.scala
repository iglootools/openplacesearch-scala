package com.sirika.openplacesearch.api.administrativedivision

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.administrativedivision.DummyAdministrativeEntities._


@RunWith(classOf[JUnitRunner])
class AdministrativeEntitySpec extends Spec with ShouldMatchers {

  describe("administrativeDivisionLevel") {
    it("should be 0 when no parent administrative division") {
      adm0.administrativeDivisionLevel should be === 0
    }

    it("should be 1 when administrative division is a first-order administrative division") {
      adm1.administrativeDivisionLevel should be === 1
    }

    it("should be 2 when parent administrative division is a second-order administrative division") {
      adm2.administrativeDivisionLevel should be === 2
    }
  }

  describe("parentAdministrativeEntityHavingLevel") {
    it("should return itself when requested level matches ADM level") {
      adm2.parentAdministrativeEntityHavingLevel(2) should be === adm2
    }

    it("should return adm1") {
      adm2.parentAdministrativeEntityHavingLevel(1) should be === adm1
    }

    it("should return adm0") {
      adm2.parentAdministrativeEntityHavingLevel(0) should be === adm0
    }

    it("should raise exception when requested level is less than 0") {
      evaluating { adm2.parentAdministrativeEntityHavingLevel(-1) } should produce [IllegalArgumentException]
    }

    it("should raise exception when requested level is more than current level") {
      evaluating { adm2.parentAdministrativeEntityHavingLevel(5) } should produce [IllegalArgumentException]
    }
  }

  describe("country should return adm0 as a country") {
    val adm2 = new DummyAdministrativeEntity(name="some adm2", parentAdministrativeEntity = Some(AdministrativeDivisions.UnitedStates.california))
    adm2.country should be === Countries.unitedStates
  }


}

