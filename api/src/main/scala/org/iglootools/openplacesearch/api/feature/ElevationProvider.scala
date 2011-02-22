package org.iglootools.openplacesearch.api.feature

trait ElevationProvider {
  def elevationInMeters: Option[Long]
  def gTopo30ElevationInMeters: Option[Long]
}