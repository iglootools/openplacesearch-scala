package com.sirika.openplacesearch.api.gisgraphy.resultparser

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api
import api.administrativedivision._
import api.feature.JtsPoint
import api.language.LanguageRepository
import com.sirika.commons.scala.io.Urls
import api.Implicits._

@RunWith(classOf[JUnitRunner])
class FullTextResultSpec extends Spec with ShouldMatchers {
  val administrativeDivisionRepository = api.ApplicationContext.getInstance(classOf[AdministrativeDivisionRepository])
  val countryRepository = api.ApplicationContext.getInstance(classOf[CountryRepository])
  val languageRepository = api.ApplicationContext.getInstance(classOf[LanguageRepository])

  describe("toPlaces") {
    val FullTextSearchForParisWithFullStyle = Urls.toInputStreamSupplier("com/sirika/openplacesearch/api/gisgraphy/fullTextSearchForParisWithFullStyle.xml")

    it("should return 10 places (fullTextSearchForParisWithFullStyle)") {
      placesCalledParis.size should be === 10
    }

    it("should correctly initialize all fields") {
      parisShouldHaveBeenCreatedCorrectly(placesCalledParis(0))
    }

    it("should correctly initialize places that only have ADM1") {
      val parisOntario = placesCalledParis(3)
      parisOntario.administrativeDivisionLevel == 2
    }

    def parisShouldHaveBeenCreatedCorrectly(place: Place) {
      // we do not use Place.hasSameContentAs for several reasons
      // 1/ No way we type all the alternate names manually in the object mothers
      // 2/ We get better error reporting this way in case a field is not initialized correctly
      // 3/ some limitations of the importers make it impossible to import the exact same object (missing ADMs, ...)
      place.country should be === referenceParis.country
      place.parentAdministrativeEntity should be === Some(AdministrativeDivisions.France.IleDeFrance.paris)
      place.currency should be === referenceParis.currency

      place.population should be === referenceParis.population
      place.gTopo30ElevationInMeters should be === referenceParis.gTopo30ElevationInMeters
      place.elevationInMeters should be === referenceParis.elevationInMeters
      place.timeZone should be === referenceParis.timeZone
      place.stableId should be === referenceParis.stableId
      place.name should be === referenceParis.name
      place.distanceTo(referenceParis) should be <= 100.0
      place.localizedNames.size should be >= 10

    }
    def referenceParis = Places.France.IleDeFrance.Paris.paris

    def placesCalledParis= new FullTextResult(FullTextSearchForParisWithFullStyle).toPlaces
  }



}