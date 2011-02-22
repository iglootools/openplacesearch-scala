package org.iglootools.openplacesearch.api.gisgraphy.resultparser
import org.scalatest.matchers.ShouldMatchers
import org.iglootools.openplacesearch.api.administrativedivision.{AdministrativeDivisions, Places, Place}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object PlaceAssertions extends ShouldMatchers {
  def shouldBeParis(place: Place, checkAlternateNames:Boolean=true) {
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
    if(checkAlternateNames)
      place.localizedNames.size should be >= 10
  }

  def referenceParis = Places.France.IleDeFrance.Paris.paris

}