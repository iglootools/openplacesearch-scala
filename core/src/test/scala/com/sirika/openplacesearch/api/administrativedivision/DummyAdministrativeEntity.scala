package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.feature.{LocalizedName, FeatureNames}
import com.google.common.base.Objects

object DummyAdministrativeEntities {
  def adm0 = new DummyAdministrativeEntity(name="adm0 name", parentAdministrativeEntity=None, userFriendlyCode = Some("adm0"))
  def adm1 = new DummyAdministrativeEntity(name="adm1 name", parentAdministrativeEntity=Some(adm0), userFriendlyCode = Some("adm1"))
  def adm2 = new DummyAdministrativeEntity(name="adm2 name", parentAdministrativeEntity=Some(adm1), userFriendlyCode = Some("adm2"))
}

class DummyAdministrativeEntity(val name: String, val parentAdministrativeEntity: Option[AdministrativeEntity], val userFriendlyCode: Option[String] = None) extends AdministrativeEntity{
  private val featureNames = FeatureNames(name)
  def shortName(language: Language): String = featureNames.shortName(language)
  def preferredName(language: Language): String = featureNames.preferredName(language)
  def localizedNames: List[LocalizedName] = featureNames.localizedNames

  override def equals(that: Any): Boolean = that match {
    case a: DummyAdministrativeEntity => name.equals(a.name)
    case _ => false
  }

  override def hashCode: Int = Objects.hashCode(name)
}

