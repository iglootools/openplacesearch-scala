package com.sirika.openplacesearch.api.formatting

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class LongUsStyleNamesExtractorSpec extends Spec with ShouldMatchers {

  describe("FullyQualifiedNamesExtractor") {
    val extractor: NamesExtractor = new LongUsStyleNamesExtractor()

    it("should extract all name parts") {
      extractor.extractRelevantNames(NameProviders.losAngeles.fullyQualifiedNameParts) should be === List("California", "Los Angeles")
    }

    it("should not accept lists that have less than 3 name parts") {
      evaluating { extractor.extractRelevantNames(NameProviders.twoPartsOnly.fullyQualifiedNameParts) } should produce [IllegalArgumentException]
    }
  }
}