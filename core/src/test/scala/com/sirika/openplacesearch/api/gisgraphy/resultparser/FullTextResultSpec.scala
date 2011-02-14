package com.sirika.openplacesearch.api.gisgraphy.resultparser

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api
import api.Implicits._
import com.sirika.commons.scala.io.{InputSupliers, Urls}

@RunWith(classOf[JUnitRunner])
class FullTextResultSpec extends Spec with ShouldMatchers {

  describe("toPlaces") {
    val FullTextSearchForParisWithFullStyle = Urls.toInputStreamSupplier("com/sirika/openplacesearch/api/gisgraphy/fullTextSearchForParisWithFullStyle.xml")

    it("should return 10 places (fullTextSearchForParisWithFullStyle)") {
      placesCalledParis.size should be === 10
    }

    it("should correctly initialize all fields") {
      PlaceAssertions.shouldBeParis(placesCalledParis(0))
    }

    it("should correctly initialize places that only have ADM1") {
      val parisOntario = placesCalledParis(3)
      parisOntario.administrativeDivisionLevel == 2
    }

    def placesCalledParis= {
      InputSupliers.doWithInputStream(FullTextSearchForParisWithFullStyle) { is =>
        new FullTextResultParser().toPlaces(is)
      }
    }
  }

}