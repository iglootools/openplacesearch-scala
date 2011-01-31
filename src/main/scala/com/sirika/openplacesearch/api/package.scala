package com.sirika.openplacesearch

import com.vividsolutions.jts.geom.{PrecisionModel, GeometryFactory, Envelope, Geometry}

package object api {
  private[api] val WGS84_SRID = 4326
  private[api] val WGS84_ENVELOPE = new Envelope(-180.0, 180.0, -90.0, 90.0)
  private[api] val Precision: PrecisionModel = new PrecisionModel(PrecisionModel.FLOATING)
  protected[api] val GeometryFactory: GeometryFactory = new GeometryFactory(Precision, WGS84_SRID)
  protected[api] val ValidCoordinateBounds: Geometry = api.GeometryFactory.toGeometry(WGS84_ENVELOPE);
}
