package org.iglootools.openplacesearch.api.formatting

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class ShortQualifiedNamesExtractorSpec extends Spec with ShouldMatchers {

  describe("FullyQualifiedNamesExtractor") {
    val extractor: NamesExtractor = new ShortQualifiedNamesExtractor()

    it("should extract country, adm1 and place") {
      extractor.extractRelevantNames(NameProviders.losAngeles.fullyQualifiedNameParts) should be === List("United States", "California", "Los Angeles")
    }

    it("should not accept lists that have less than 3 name parts") {
      evaluating { extractor.extractRelevantNames(NameProviders.twoPartsOnly.fullyQualifiedNameParts) } should produce [IllegalArgumentException]
    }
  }
}