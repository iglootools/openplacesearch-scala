package com.sirika.openplacesearch.api.formatting

import internal.{ShortUsStyleNamesExtractor, NamesExtractor}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class ShortUsStyleNamesExtractorSpec extends Spec with ShouldMatchers {

  describe("FullyQualifiedNamesExtractor") {
    val extractor: NamesExtractor = new ShortUsStyleNamesExtractor()

    it("should extract ADM1 code and place") {
      extractor.extractRelevantNames(NameProviders.losAngeles.fullyQualifiedNameParts) should be === List("CA", "Los Angeles")
    }

    it("should not accept lists that have less than 3 name parts") {
      evaluating { extractor.extractRelevantNames(NameProviders.twoPartsOnly.fullyQualifiedNameParts) } should produce [IllegalArgumentException]
    }
  }
}