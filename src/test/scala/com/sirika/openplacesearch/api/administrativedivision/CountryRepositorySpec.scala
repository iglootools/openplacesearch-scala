package com.sirika.openplacesearch.api.administrativedivision

import internal.InMemoryCountryRepository
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api

@RunWith(classOf[JUnitRunner])
class CountryRepositorySpec extends Spec with ShouldMatchers {
  val countryRepository = api.ApplicationContext.getInstance(classOf[CountryRepository])

  describe("findAll") {

    it("should find 251 countries") {
      countryRepository.findAll.size should be === 251
    }
  }

  describe("getByIsoAlpha3Code") {
    describe("should retrieve France") {
      countryRepository.getByIsoAlpha3Code("FRA") should be === Countries.france
    }

    describe("should produce exception for unknown code") {
      evaluating { countryRepository.getByIsoAlpha3Code("ZZZ")} should produce [NoSuchElementException]
    }
  }

  describe("getByIsoAlpha2Code") {
    describe("should retrieve France") {
      countryRepository.getByIsoAlpha2Code("FR") should be === Countries.france
    }

    describe("should produce exception for unknown code") {
      evaluating { countryRepository.getByIsoAlpha2Code("ZZZ")} should produce [NoSuchElementException]
    }
  }

  describe("getByFipsCode") {
    describe("should retrieve France") {
      countryRepository.getByFipsCode("FR") should be === Countries.france
    }

    describe("should produce exception for unknown code") {
      evaluating { countryRepository.getByFipsCode("ZZZ")} should produce [NoSuchElementException]
    }
  }
}

