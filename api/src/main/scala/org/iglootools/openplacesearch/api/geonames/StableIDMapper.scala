package org.iglootools.openplacesearch.api.geonames

trait StableIDMapper {
  def stableID(originalID: String): Option[String]
  def originalID(stableID: String): Option[String]
}