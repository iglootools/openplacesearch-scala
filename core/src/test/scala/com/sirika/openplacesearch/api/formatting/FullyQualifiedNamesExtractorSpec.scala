package com.sirika.openplacesearch.api.formatting

import internal.{NamesExtractor, FullyQualifiedNamesExtractor}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
@RunWith(classOf[JUnitRunner])
class FullyQualifiedNamesExtractorSpec extends Spec with ShouldMatchers {

  describe("FullyQualifiedNamesExtractor") {
    val extractor: NamesExtractor = new FullyQualifiedNamesExtractor()

    it("should extract all name parts") {
      extractor.extractRelevantNames(NameProviders.losAngeles.fullyQualifiedNameParts) should be === List("United States", "California", "Los Angeles County", "Los Angeles")
    }
  }
}