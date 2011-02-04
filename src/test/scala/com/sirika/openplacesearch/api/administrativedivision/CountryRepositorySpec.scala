package com.sirika.openplacesearch.api.administrativedivision

import internal.InMemoryCountryRepository
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class CountryRepositorySpec extends Spec with ShouldMatchers {

  describe("CountryRepository") {
    it("should not crash") {
      new InMemoryCountryRepository()
    }

  }
}

