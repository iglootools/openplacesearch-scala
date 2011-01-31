package com.sirika.openplacesearch

import com.vividsolutions.jts.geom.{PrecisionModel, GeometryFactory, Envelope, Geometry}

package object api {
  val WGS84_SRID = 4326
  val WGS84_ENVELOPE = new Envelope(-180.0, 180.0, -90.0, 90.0)
  val Precision: PrecisionModel = new PrecisionModel(PrecisionModel.FLOATING)
  val GeometryFactory: GeometryFactory = new GeometryFactory(Precision, WGS84_SRID)
  val ValidCoordinateBounds: Geometry = api.GeometryFactory.toGeometry(WGS84_ENVELOPE);
}
