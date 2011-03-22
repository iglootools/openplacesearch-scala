package org.iglootools.openplacesearch.api.gisgraphy;
trait StableIDMapper {
  def stableID(originalID: String): Option[String]
  def originalID(stableID: String): Option[String]
}