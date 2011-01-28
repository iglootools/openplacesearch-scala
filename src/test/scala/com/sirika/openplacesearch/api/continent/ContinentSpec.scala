package com.sirika.openplacesearch.api.continent

import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.continent.Continents._

@RunWith(classOf[JUnitRunner])
class ContinentSpec extends Spec with ShouldMatchers {
    describe("Continent") {
        describe("constructor") {
            it("should require a non-empty geonames code") {
                evaluating { Continent(geonamesCode="", name="Europe") } should produce [IllegalArgumentException]
            }

            it("should require a non-empty name") {
                evaluating { Continent(geonamesCode="EU", name="") } should produce [IllegalArgumentException]
            }
        }

        describe("toString") {
            it("should return geonamesCode and name") {
                europe.toString should be === "Continent{geonamesCode=EU, name=Europe}"
            }
        }
    }

    describe("A given language") {
        it("should be equal to another language that has the same alpha3Code") {
            europe should be === continentWithSameCodeAsEurope
        }

        it("should have the same hashcode as another language that has the same alpha3Code") {
            europe.hashCode should be === continentWithSameCodeAsEurope.hashCode
        }

        it("should not be equal to another language that has a different alpha3Code") {
            europe should not be africa
        }

        it("should not have the same hashcode as another language that has a different alpha3Code") {
            europe.hashCode should not be africa.hashCode
        }
    }
    
    def continentWithSameCodeAsEurope() = Continent(geonamesCode="EU", name="anything")
}