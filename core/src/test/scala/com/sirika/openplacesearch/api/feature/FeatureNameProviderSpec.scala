package com.sirika.openplacesearch.api.feature

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.administrativedivision.DummyAdministrativeEntities._

@RunWith(classOf[JUnitRunner])
class FeatureNameProviderSpec extends Spec with ShouldMatchers {

  describe("FeatureNameProvider") {
    it("should return all NameParts") {
      adm2.fullyQualifiedNameParts should be ===
        List(
          NamePart(name="adm0 name", userFriendlyCode = Some("adm0")),
          NamePart(name="adm1 name", userFriendlyCode = Some("adm1")),
          NamePart(name="adm2 name", userFriendlyCode = Some("adm2")))
    }


  }
}