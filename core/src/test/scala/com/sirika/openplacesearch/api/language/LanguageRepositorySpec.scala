package com.sirika.openplacesearch.api.language

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.language.Languages._
import com.sirika.openplacesearch.api

@RunWith(classOf[JUnitRunner])
class LanguageRepositorySpec extends Spec with ShouldMatchers {
  val languageRepository = api.DefaultApplicationContext.getInstance(classOf[LanguageRepository])

  describe("getByAlpha2Code") {
    it("should find the french language") {
      languageRepository.getByAlpha2Code("fr") should be === french
    }

    it("should produce an exception for inexisting language") {
      evaluating { languageRepository.getByAlpha2Code("zz") } should produce[NoSuchElementException]
    }

    it("should be case insensitive") {
      languageRepository.getByAlpha2Code("FR") should be === french
    }
  }

  describe("maybeGetByAlpha2Code") {
    it("should find the french language") {
      languageRepository.maybeGetByAlpha2Code("fr") should be === Some(french)
    }

    it("should return None for inexisting language") {
      languageRepository.maybeGetByAlpha2Code("zz") should be === None
    }

    it("should be case insensitive") {
      languageRepository.maybeGetByAlpha2Code("FR") should be === Some(french)
    }
  }


  describe("getByAlpha3Code") {
    it("should find the french language") {
      languageRepository.getByAlpha3Code("fra") should be === french
    }

    it("should be case insensitive") {
      languageRepository.getByAlpha3Code("FRA") should be === french
    }

    it("shouldproduce an exception for inexisting language") {
      evaluating { languageRepository.getByAlpha3Code("zzz")  } should produce[NoSuchElementException]
    }
  }

  describe("maybeGetByAlpha3Code") {
    it("should find the french language") {
      languageRepository.maybeGetByAlpha3Code("fra") should be === Some(french)
    }

    it("should be case insensitive") {
      languageRepository.maybeGetByAlpha3Code("FRA") should be === Some(french)
    }

    it("shouldproduce an exception for inexisting language") {
      languageRepository.maybeGetByAlpha3Code("zzz")  should be === None
    }
  }

  describe("findAllLanguages") {
    it("should retrieve 7600 languages") {
      languageRepository.findAll.size should be === 7600
    }
  }
}