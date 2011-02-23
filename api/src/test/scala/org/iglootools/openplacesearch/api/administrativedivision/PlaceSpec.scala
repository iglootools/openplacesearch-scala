package org.iglootools.openplacesearch.api.administrativedivision

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.openplacesearch.samples.{Countries, Places}

@RunWith(classOf[JUnitRunner])
class PlaceSpec extends Spec with ShouldMatchers {

  describe("Place") {
    it("should be compareable on its name") {
      Places.UnitedStates.California.LosAngelesCounty.losAngeles should be < Places.UnitedStates.California.LosAngelesCounty.marinaDelRey
      Places.UnitedStates.California.LosAngelesCounty.losAngeles should be < Places.France.IleDeFrance.Paris.paris
      Places.UnitedStates.California.LosAngelesCounty.losAngeles should be < Places.France.IleDeFrance.Yvelines.rambouillet
    }

    it("should return its country currency") {
      Places.UnitedStates.California.LosAngelesCounty.losAngeles.currency should be === Countries.unitedStates.currency
    }


  }
}

