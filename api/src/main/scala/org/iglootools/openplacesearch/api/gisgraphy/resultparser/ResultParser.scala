package org.iglootools.openplacesearch.api.gisgraphy.resultparser

import org.iglootools.openplacesearch.api.administrativedivision.Place
import java.io.InputStream

protected[gisgraphy] trait ResultParser {
  def toPlaces(inputStream: InputStream): List[Place]
}