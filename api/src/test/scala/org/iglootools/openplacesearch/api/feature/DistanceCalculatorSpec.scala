package org.iglootools.openplacesearch.api.feature

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.vividsolutions.jts.geom.Point
import javax.measure.unit.SI
import org.iglootools.openplacesearch.samples.Places

@RunWith(classOf[JUnitRunner])
class DistanceCalculatorSpec extends Spec with ShouldMatchers {

  describe("DistanceCalculator") {
    val distanceCalculatorFromGazeran = new DistanceCalculator() with LocationProvider {
        def location: Point = Places.France.IleDeFrance.Yvelines.gazeran.location
      }
    it("should calculate the distance in meters between gazeran and rambouillet") {
      distanceCalculatorFromGazeran.distanceTo(Places.France.IleDeFrance.Yvelines.rambouillet) should be (5200.0 plusOrMinus 100)
    }

    it("should calculate the distance in km between gazeran and rambouillet") {
      distanceCalculatorFromGazeran.distanceTo(destination=Places.France.IleDeFrance.Yvelines.rambouillet, unit=SI.KILO(SI.METER)) should be (5.2 plusOrMinus 0.1)
    }
  }
}