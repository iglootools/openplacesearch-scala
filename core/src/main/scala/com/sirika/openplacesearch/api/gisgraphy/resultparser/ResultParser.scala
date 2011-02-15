package com.sirika.openplacesearch.api.gisgraphy.resultparser

import com.sirika.openplacesearch.api.administrativedivision.Place
import java.io.InputStream

protected[gisgraphy] trait ResultParser {
  def toPlaces(inputStream: InputStream): List[Place]
}