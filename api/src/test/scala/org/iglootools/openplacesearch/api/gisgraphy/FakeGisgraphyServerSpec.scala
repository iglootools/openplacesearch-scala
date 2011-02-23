package org.iglootools.openplacesearch.api.gisgraphy

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.commons.scala.io.{InputSupliers, Urls}
import org.iglootools.openplacesearch.samples.Places


@RunWith(classOf[JUnitRunner])
class FakeGisgraphyServerSpec extends Spec with ShouldMatchers {

  describe("Fake Gisgraphy Server") {
    val FullTextSearchForParisWithFullStyle = Urls.toInputStreamSupplier("org/iglootools/openplacesearch/api/gisgraphy/fullTextSearchForParisWithFullStyle.xml")
    val GeolocalizationResultNearParis = Urls.toInputStreamSupplier("org/iglootools/openplacesearch/api/gisgraphy/geolocalizationNearParis.xml")

    val gisgraphyServer: GisgraphyServer = FakeGisgraphyServer {
      case FullTextQuery(query, _) if query.toLowerCase == "paris" => Some(FullTextSearchForParisWithFullStyle)
      case GeolocalizationQuery(location, radius, _) if location == Places.France.IleDeFrance.Paris.paris => Some(GeolocalizationResultNearParis)
      case _ => None
    }

    it("should return full text results for Paris") {
      val results = gisgraphyServer.newFullTextQuery("Paris").execute
      results.size should be > 0
    }

    it("should return no result for Los Angeles") {
      val results = gisgraphyServer.newFullTextQuery("Los Angeles").execute
      results.size should be === 0
    }

    it("should return geolocalization results for Paris") {
      val results = gisgraphyServer.newGeolocalizationQuery(Places.France.IleDeFrance.Paris.paris, 50000).execute
      results.size should be > 0
    }

    it("should return no geolocalization result for Los Angeles") {
      val results = gisgraphyServer.newGeolocalizationQuery(Places.UnitedStates.California.LosAngelesCounty.losAngeles, 50000).execute
      results.size should be === 0
    }

    it("should return only the requested results") {
      val thirdResultFromParis = gisgraphyServer.newFullTextQuery("Paris").execute(2)
      val results = gisgraphyServer.newFullTextQuery("Paris", Pagination(firstResult = 3, numberOfResults = 5)).execute
      results.size should be === 5
      results(0) should be === thirdResultFromParis
    }
  }

}