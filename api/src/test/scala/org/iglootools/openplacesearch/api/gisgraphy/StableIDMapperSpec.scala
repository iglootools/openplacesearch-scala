package org.iglootools.openplacesearch.api.gisgraphy

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import org.iglootools.commons.scala.io.Urls
import org.iglootools.openplacesearch.samples.Places
import java.io.{OutputStreamWriter, FileOutputStream}
import com.google.common.base.Charsets

@RunWith(classOf[JUnitRunner])
class StableIDMapperSpec extends Spec with ShouldMatchers {

  describe("StableIDMapper") {
    val stableIDMapper: StableIDMapper = new GisgraphyStableIDMapper

    it("should find paris stableID") {
      stableIDMapper.stableID("2988507") should be === Some("paris")
    }

    it("should find paris original ID") {
      stableIDMapper.originalID("paris") should be === Some("2988507")
    }
  }
   /*
  describe("temp") {
    val out = new FileOutputStream("/tmp/out")
    val writer = new OutputStreamWriter(out, Charsets.UTF_8)
    GisgraphyStableIDMapper.Mapping.foreach {
      (k: String, v: String) =>


    }
  }  */

}