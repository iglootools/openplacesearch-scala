package com.sirika.openplacesearch.api.continent

import com.sirika.openplacesearch.api.continent.internal.InMemoryContinentRepository
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.continent.Continents._

@RunWith(classOf[JUnitRunner])
class ContinentRepositorySpec extends Spec with ShouldMatchers {
  val continentRepository : ContinentRepository = new InMemoryContinentRepository()
  describe("findByGeonamesCode") {
    it("should find Europe") {
      continentRepository.findByGeonamesCode("EU") should be === Some(europe)
    }

    it("should not find inexisting continent") {
      continentRepository.findByGeonamesCode("ZZ") should be === None
    }
  }

  describe("findAll") {
    it("should find 7 continents") {
      continentRepository.findAll.size should be === 7
    }
  }
}