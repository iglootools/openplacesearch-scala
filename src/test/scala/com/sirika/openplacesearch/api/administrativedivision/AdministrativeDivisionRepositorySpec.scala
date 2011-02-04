package com.sirika.openplacesearch.api.administrativedivision

import internal.{InMemoryAdministrativeDivisionRepository, InMemoryCountryRepository}
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec

@RunWith(classOf[JUnitRunner])
class AdministrativeDivisionRepositorySpec extends Spec with ShouldMatchers {
  val administrativeDivisionRepository: AdministrativeDivisionRepository= new InMemoryAdministrativeDivisionRepository()

  describe("findAll") {

    it("should not crash") {

    }
  }
}

