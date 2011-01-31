package com.sirika.openplacesearch.api.gisfeature

trait ElevationProvider {
  def elevationInMeters: Option[Long]
  def gTopo30ElevationInMeters: Option[Long]
}