package com.sirika.openplacesearch.api.gisgraphy

import http.HttpGisgraphyServer
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.ImplicitDependencyInjection._
import com.sirika.openplacesearch.api.administrativedivision.Places

@RunWith(classOf[JUnitRunner])
class GisgraphyServerSpec extends Spec with ShouldMatchers {

  val gisgraphyServer: GisgraphyServer = new HttpGisgraphyServer(baseUrl="http://services.gisgraphy.com")
  describe("GisgraphyServer") {
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
  }

}