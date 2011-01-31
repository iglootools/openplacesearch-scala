package com.sirika.openplacesearch.api.gisfeature

import javax.measure.quantity.Length
import javax.measure.unit.SI
import com.vividsolutions.jts.geom.Point
import org.geotools.referencing.crs.DefaultGeographicCRS
import org.geotools.geometry.jts.JTS

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait DistanceCalculator {
  self: LocationProvider =>

  def distanceTo(destination: LocationProvider, unit: javax.measure.unit.Unit[Length] = SI.METER) : Double = {
    SI.METER.getConverterTo(unit).convert(
      JTS.orthodromicDistance(self.coordinate, destination.coordinate, DefaultGeographicCRS.WGS84))
  }
}