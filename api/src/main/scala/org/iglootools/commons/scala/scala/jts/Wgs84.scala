package org.iglootools.commons.scala.jts;

import com.vividsolutions.jts.geom.{PrecisionModel, GeometryFactory, Envelope, Geometry}

object Wgs84 {
  val WGS84_SRID = 4326
  val WGS84_ENVELOPE = new Envelope(-180.0, 180.0, -90.0, 90.0)
  val Precision: PrecisionModel = new PrecisionModel(PrecisionModel.FLOATING)
  val GeometryFactory: GeometryFactory = new GeometryFactory(Precision, WGS84_SRID)
  val ValidCoordinateBounds: Geometry = GeometryFactory.toGeometry(WGS84_ENVELOPE);
}