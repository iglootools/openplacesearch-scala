package com.sirika.commons.scala.jts
import com.vividsolutions.jts.geom.{Coordinate, Point}

object JtsPoint {
  def apply(longitude: Double, latitude: Double): Point = Wgs84.GeometryFactory.createPoint(new Coordinate(longitude, latitude))
  def unapply(p: Point):Option[(Double,Double)] = Some((p.getCoordinate().x, p.getCoordinate().y))
}
