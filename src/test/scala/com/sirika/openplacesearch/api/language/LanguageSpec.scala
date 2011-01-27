package com.sirika.openplacesearch.api.language

import org.scalatest.junit.JUnitRunner

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.language.Languages._
import com.sirika.openplacesearch.api.language._

@RunWith(classOf[JUnitRunner])
class LanguageSpec extends Spec with ShouldMatchers {
    describe("toString ") {
        it("should contain name, alpha3 and alpha2 for French") {
            french.toString should be === "Language{name=Français, alpha3=fra, alpha2=Some(fr)}"
        }
        
        it("should contain name, alpha3 for Hawaiian") {
            hawaiian.toString should be === "Language{name=Hawaiian, alpha3=haw, alpha2=None}"
        }
    }
    
    describe("Language") {
        it("should require a non-empty alpha3 code") {
            evaluating { Language(name="Hawaiian", alpha3Code="") } should produce [IllegalArgumentException]
        }
        
        it("should require a non-empty name") {
            evaluating { Language(name="", alpha3Code="haw") } should produce [IllegalArgumentException]
        }
    }
    
    describe("A given Language") {
        it("should be equal to another language that has the same alpha3Code") {
            french should be === french
        }
        
        it("should have the same hashcode as another language that has the same alpha3Code") {
            french.hashCode should be === french.hashCode
        }
        
        it("should not be equal to another language that has a different alpha3Code") {
            french should not be hawaiian
        }
        
        it("should not have the same hashcode as another language that has a different alpha3Code") {
            french.hashCode should not be hawaiian.hashCode
        }
    }
}