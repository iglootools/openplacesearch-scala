package org.iglootools.openplacesearch.api.administrativedivision

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.openplacesearch.api
import org.iglootools.openplacesearch.samples.{Languages, Countries}

@RunWith(classOf[JUnitRunner])
class CountryRepositorySpec extends Spec with ShouldMatchers {
  val countryRepository = api.ImplicitDependencyInjection.countryRepository

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

  describe("imported countries") {
    describe("should have localized names") {
      countryRepository.getByIsoAlpha2Code("US").preferredName(Languages.french) should be === "États-Unis"
      countryRepository.getByIsoAlpha2Code("US").localizedNames.size should be >= 10
    }
  }
}

