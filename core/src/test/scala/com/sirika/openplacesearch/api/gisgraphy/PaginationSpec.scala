package com.sirika.openplacesearch.api.gisgraphy

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class PaginationSpec extends Spec with ShouldMatchers {

  describe("Pagination") {
    it("endResult should be 1 when firstResult=1 and numberOfResults=1") {
      Pagination(firstResult = 1, numberOfResults = 1).endResult should be === 1
    }

    it("endResult should be 5 when firstResult=1 and numberOfResults=4") {
      Pagination(firstResult = 1, numberOfResults = 5).endResult should be === 5
    }
  }

}