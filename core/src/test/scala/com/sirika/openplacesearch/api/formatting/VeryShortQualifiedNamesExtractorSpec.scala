package com.sirika.openplacesearch.api.formatting

import internal.{VeryShortQualifiedNamesExtractor, NamesExtractor}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class VeryShortQualifiedNamesExtractorSpec extends Spec with ShouldMatchers {

  describe("FullyQualifiedNamesExtractor") {
    val extractor: NamesExtractor = new VeryShortQualifiedNamesExtractor()

    it("should extract country and place") {
      extractor.extractRelevantNames(NameProviders.losAngeles.fullyQualifiedNameParts) should be === List("United States", "Los Angeles")
    }

    it("should not accept lists that have less than 2 name parts") {
      evaluating { extractor.extractRelevantNames(NameProviders.onePartOnly.fullyQualifiedNameParts) } should produce [IllegalArgumentException]
    }
  }
}