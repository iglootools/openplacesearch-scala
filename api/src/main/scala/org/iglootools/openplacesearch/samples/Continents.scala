package org.iglootools.openplacesearch.samples

import org.iglootools.openplacesearch.api.continent.Continent

object Continents {
  def europe = Continent(geonamesCode="EU", name="Europe")
  def africa = Continent(geonamesCode="AF", name="Africa")
  def northAmerica = Continent(geonamesCode="NA", name="North America")
  def antarctica = Continent(geonamesCode="AN", name="Antarctica")
  def oceania = Continent(geonamesCode="OC", name="Oceania")
}