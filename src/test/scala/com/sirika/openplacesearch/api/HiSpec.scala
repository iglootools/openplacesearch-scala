package com.sirika.openplacesearch.api

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class HiSpec extends Spec with ShouldMatchers {
    describe("Hi") {
        it("should say hi") {
            assert(1 === 1)
        }
        it("should not crash") {
            1 should be === 1
        }
    }
}