package com.sirika.openplacesearch.api.gisgraphy.http

import com.sirika.openplacesearch.api.feature.LocationProvider
;


final class GeolocalizationQuery(private[this] val location: LocationProvider,
                                      private[this] val radiusInMeters: Int,
                                      private[this] val pagination: Pagination=Pagination(firstResult=1,numberOfResults=10)) {
  require(location != null, "location is required")
  require(radiusInMeters > 0, "radius must be a positive number")
  require(pagination != null, "pagination is required")


  def toUrl(baseUrl: String): String = {
    "%s/geoloc/geolocsearch?lat=%f&lng=%f&radius=%d&placetype=city&format=XML&__checkbox_indent=true&from=%d&to=%d".format(
      baseUrl,
      location.latitude,
      location.longitude,
      radiusInMeters,
      pagination.firstResult,
      pagination.endResult
    )
  }
}