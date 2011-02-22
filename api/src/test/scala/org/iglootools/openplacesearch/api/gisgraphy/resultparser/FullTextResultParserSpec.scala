package org.iglootools.openplacesearch.api.gisgraphy.resultparser

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.openplacesearch.api
import api.ImplicitDependencyInjection._
import org.iglootools.commons.scala.io.{InputSupliers, Urls}

@RunWith(classOf[JUnitRunner])
class FullTextResultParserSpec extends Spec with ShouldMatchers {

  describe("toPlaces") {
    val FullTextSearchForParisWithFullStyle = Urls.toInputStreamSupplier("org/iglootools/openplacesearch/api/gisgraphy/fullTextSearchForParisWithFullStyle.xml")

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