package com.sirika.openplacesearch.api.formatting

import com.sirika.openplacesearch.api.language.internal.InMemoryLanguageRepository
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.language.Languages._
import com.sirika.openplacesearch.api.language._

@RunWith(classOf[JUnitRunner])
class ConcatenatingNameFormatterSpec extends Spec with ShouldMatchers {

  describe("ConcatenatingNameFormatter") {
    it("should display the nameparts in forward order") {
      val formatter: NameFormatter = new ConcatenatingNameFormatter(reverse=false)
      formatter.formatName(NameProviders.losAngeles) should be === "United States, California, Los Angeles County, Los Angeles"
    }

    it("should display the nameparts in reverse order") {
      val formatter: NameFormatter = new ConcatenatingNameFormatter(reverse=true)
      formatter.formatName(NameProviders.losAngeles) should be === "Los Angeles, Los Angeles County, California, United States"
    }

    it("should display the nameparts separated by the given separator") {
      val formatter: NameFormatter = new ConcatenatingNameFormatter(separator = " | ")
      formatter.formatName(NameProviders.losAngeles) should be === "Los Angeles | Los Angeles County | California | United States"
    }

    it("should use the given extractor") {
      val formatter: NameFormatter = new ConcatenatingNameFormatter(namesExtractor = new VeryShortQualifiedNamesExtractor())
      formatter.formatName(NameProviders.losAngeles) should be === "Los Angeles, United States"
    }
  }
}