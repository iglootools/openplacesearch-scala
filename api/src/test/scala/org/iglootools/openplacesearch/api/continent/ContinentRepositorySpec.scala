package org.iglootools.openplacesearch.api.continent

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.openplacesearch.api.continent.Continents._
import org.iglootools.openplacesearch.api

@RunWith(classOf[JUnitRunner])
class ContinentRepositorySpec extends Spec with ShouldMatchers {
  val continentRepository = api.DefaultApplicationContext.getInstance(classOf[ContinentRepository])

  describe("getByGeonamesCode") {
    it("should find Europe") {
      continentRepository.getByGeonamesCode("EU") should be === europe
    }

    it("should not find inexisting continent") {
      evaluating { continentRepository.getByGeonamesCode("ZZ") } should produce[NoSuchElementException]
    }
  }

  describe("findAll") {
    it("should find 7 continents") {
      continentRepository.findAll.size should be === 7
    }
  }
}