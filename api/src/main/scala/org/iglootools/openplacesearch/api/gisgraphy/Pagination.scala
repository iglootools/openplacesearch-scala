package org.iglootools.openplacesearch.api.gisgraphy

final case class Pagination(val firstResult: Int, val numberOfResults: Int) {
  require(firstResult >=1, "the first result is 1")
  require(numberOfResults >=1, "we cannot retrieve less than 1 result")

  def endResult: Int = firstResult + numberOfResults -1
}

