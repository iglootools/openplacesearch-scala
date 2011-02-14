package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.feature.{FeatureNameProvider, LocalizedName, ParentAdministrativeEntityProvider}
import com.google.common.base.{Objects}

/**
 * An Administrative Division
 * <p>The code is unique only among a given country </p>
 * @param code represents the <a href="http://en.wikipedia.org/wiki/List_of_FIPS_region_codes">FIPS code</a> for US states.
 * @author Sami Dalouche (sami.dalouche@gmail
 */
final case class AdministrativeDivision(val code: String,
                                        val featureNameProvider: FeatureNameProvider,
                                        val parentAdministrativeEntityProvider: ParentAdministrativeEntityProvider) extends AdministrativeEntity {
  country match {
    case c: Country => // ok
    case _ => throw new IllegalArgumentException("parentAdministrativeEntityProvider should have a country in its hierarchy")
  }

  require(Option(code) exists {_.nonEmpty}, "the code is required")

  // FeatureNameProvider
  def name: String = featureNameProvider.name
  def shortName(language: Language): String = featureNameProvider.shortName(language)
  def preferredName(language: Language): String = featureNameProvider.preferredName(language)
  def localizedNames: List[LocalizedName] = featureNameProvider.localizedNames
  def userFriendlyCode: Option[String] = Some(code)

  // ParentAdministrativeEntityProvider
  def parentAdministrativeEntity: Option[AdministrativeEntity] = parentAdministrativeEntityProvider.parentAdministrativeEntity

  override def hashCode(): Int = Objects.hashCode(parentAdministrativeEntity, code)

  override def equals(that: Any): Boolean =   that match {
    case a: AdministrativeDivision => Objects.equal(parentAdministrativeEntity, a.parentAdministrativeEntity) &&  Objects.equal(code, a.code)
    case _ => false
  }


}