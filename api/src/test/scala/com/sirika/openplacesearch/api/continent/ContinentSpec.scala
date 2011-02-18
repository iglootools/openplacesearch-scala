package com.sirika.openplacesearch.api.continent

import com.sirika.commons.scala.SanityChecks
import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.continent.Continents._

@RunWith(classOf[JUnitRunner])
class ContinentSpec extends Spec with ShouldMatchers {
  describe("constructor") {
    it("should require a non-empty geonames code") {
      evaluating { Continent(geonamesCode="", name="Europe") } should produce [IllegalArgumentException]
    }

    it("should require a non-empty name") {
      evaluating { Continent(geonamesCode="EU", name="") } should produce [IllegalArgumentException]
    }
  }

  describe("toString") {
    it("should return geonamesCode and name") {
      europe.toString should be === "Continent{geonamesCode=EU, name=Europe}"
    }
  }

  describe("hashCode and equals") {
    it("should be sane") {
      SanityChecks.hashCodeAndEqualsShouldBeSane(europe, eqObj=europe, neObj=africa)
    }
  }
}
