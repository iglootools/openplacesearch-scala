package org.iglootools.openplacesearch.api.administrativedivision

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.openplacesearch.samples.AdministrativeDivisions

@RunWith(classOf[JUnitRunner])
class AdministrativeDivisionSpec extends Spec with ShouldMatchers {

  describe("AdministrativeDivision") {
    it("should use its ADM code as userFriendlyCode") {
      AdministrativeDivisions.UnitedStates.california.userFriendlyCode should be === Some("CA")
    }


  }
}

