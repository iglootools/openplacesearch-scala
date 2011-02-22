package org.iglootools.openplacesearch.tests

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.openplacesearch.api._
import org.iglootools.openplacesearch.api.administrativedivision.Places

@RunWith(classOf[JUnitRunner])
class GisgraphyServerSpec extends Spec with ShouldMatchers {
  describe("GisgraphyServer") {
    val gisgraphyServer: GisgraphyServer = HttpGisgraphyServer(baseUrl="http://services.gisgraphy.com")

    it("should perform fulltext query") {
      val result = gisgraphyServer.newFullTextQuery("paris", Pagination(firstResult=1, numberOfResults=7)).execute
      result.size should be === 7
    }

    it("should perform geoloc query") {
      val result = gisgraphyServer.newGeolocalizationQuery(
        location=Places.France.IleDeFrance.Paris.paris,
        radiusInMeters=10000,
        pagination=Pagination(firstResult=1, numberOfResults=7)).execute
      result.size should be === 7
    }

    it("should not crash when query contains special characters") {
      val result = gisgraphyServer.newFullTextQuery("los & angeles", Pagination(firstResult=1, numberOfResults=7)).execute
      result.size should be > 0
    }
  }
}