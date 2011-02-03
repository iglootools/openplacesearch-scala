package com.sirika.openplacesearch.api.administrativedivision

import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.Spec
import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.feature.{LocalizedName, FeatureNames}
import com.google.common.base.Objects

@RunWith(classOf[JUnitRunner])
class AdministrativeEntitySpec extends Spec with ShouldMatchers {

  describe("administrativeDivisionLevel") {
    it("should be 0 when no parent administrative division") {
      adm0.administrativeDivisionLevel should be === 0
    }

    it("should be 1 when administrative division is a first-order administrative division") {
      adm1.administrativeDivisionLevel should be === 1
    }

    it("should be 2 when parent administrative division is a second-order administrative division") {
      adm2.administrativeDivisionLevel should be === 2
    }
  }

  describe("parentAdministrativeEntityHavingLevel") {
    it("should return itself when requested level matches ADM level") {
      adm2.parentAdministrativeEntityHavingLevel(2) should be === adm2
    }

    it("should return adm1") {
      adm2.parentAdministrativeEntityHavingLevel(1) should be === adm1
    }

    it("should return adm0") {
      adm2.parentAdministrativeEntityHavingLevel(0) should be === adm0
    }

    it("should raise exception when requested level is less than 0") {
      evaluating { adm2.parentAdministrativeEntityHavingLevel(-1) } should produce [IllegalArgumentException]
    }

    it("should raise exception when requested level is more than current level") {
      evaluating { adm2.parentAdministrativeEntityHavingLevel(5) } should produce [IllegalArgumentException]
    }
  }

  describe("country should return adm0 as a country") {
    val adm2 = new DummyAdministrativeEntity(name="some adm2", parentAdministrativeEntity = Some(AdministrativeDivisions.UnitedStates.california))
    adm2.country should be === Countries.unitedStates
  }

  private def adm0 = new DummyAdministrativeEntity(name="adm0", parentAdministrativeEntity=None)
  private def adm1 = new DummyAdministrativeEntity(name="adm1", parentAdministrativeEntity=Some(adm0))
  private def adm2 = new DummyAdministrativeEntity(name="adm2", parentAdministrativeEntity=Some(adm1))
}

private class DummyAdministrativeEntity(val name: String, val parentAdministrativeEntity: Option[AdministrativeEntity]) extends AdministrativeEntity{
  private val featureNames = FeatureNames(name)
  def shortName(language: Language): String = featureNames.shortName(language)
  def preferredName(language: Language): String = featureNames.preferredName(language)
  def localizedNames: List[LocalizedName] = featureNames.localizedNames
  def userFriendlyCode: Option[String] = None

  override def equals(that: Any): Boolean = that match {
    case a: DummyAdministrativeEntity => name.equals(a.name)
    case _ => false
  }

  override def hashCode: Int = Objects.hashCode(name)
}