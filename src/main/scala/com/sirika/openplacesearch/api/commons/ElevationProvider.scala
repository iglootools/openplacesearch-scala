package com.sirika.openplacesearch.api.commons

trait ElevationProvider {
  def elevationInMeters: Option[Long]
  def gTopo30ElevationInMeters: Option[Long]
}