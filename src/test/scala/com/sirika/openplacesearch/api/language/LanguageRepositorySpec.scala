package com.sirika.openplacesearch.api.language

import com.sirika.openplacesearch.api.language.internal.InMemoryLanguageRepository
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.language.Languages._
import com.sirika.openplacesearch.api.language._

@RunWith(classOf[JUnitRunner])
class LanguageRepositorySpec extends Spec with ShouldMatchers {
  val languageRepository : LanguageRepository = new InMemoryLanguageRepository()

  describe("getByAlpha2Code") {
    it("should find the french language") {
      languageRepository.getByAlpha2Code("fr") should be === french
    }

    it("should not find an inexisting language") {
      evaluating { languageRepository.getByAlpha2Code("zz") } should produce[NoSuchElementException]
    }
  }

  describe("getByAlpha3Code") {
    it("should find the french language") {
      languageRepository.getByAlpha3Code("fra") should be === french
    }

    it("should not find an inexisting language") {
       evaluating { languageRepository.getByAlpha3Code("zzz")  } should produce[NoSuchElementException]
    }
  }

  describe("findAllLanguages") {
    it("should retrieve 7600 languages") {
      languageRepository.findAll.size should be === 7600
    }
  }
}