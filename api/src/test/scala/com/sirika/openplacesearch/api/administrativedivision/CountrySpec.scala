package com.sirika.openplacesearch.api.administrativedivision

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
@RunWith(classOf[JUnitRunner])
class CountrySpec extends Spec with ShouldMatchers {

  describe("Country") {
    it("should use its ISO alpha2 code as userFriendlyCode") {
      Countries.unitedStates.userFriendlyCode should be === Some("US")
    }

    it("should have no parent ADM") {
      Countries.unitedStates.parentAdministrativeEntity should be (None)
    }


  }
}

