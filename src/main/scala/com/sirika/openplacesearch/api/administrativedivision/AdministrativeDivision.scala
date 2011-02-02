package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.feature.{FeatureNameProvider, LocalizedName, ParentAdministrativeEntityProvider}

/**
 * @param code represents the <a href="http://en.wikipedia.org/wiki/List_of_FIPS_region_codes">FIPS code</a> for US states.
 * @author Sami Dalouche (sami.dalouche@gmail
 */
final case class AdministrativeDivision(
  val code: String,
  val name: String,
  val featureNameProvider: FeatureNameProvider,
  val parentAdministrativeEntityProvider: ParentAdministrativeEntityProvider) extends AdministrativeEntity {

  // FeatureNameProvider
  def shortName(language: Language): String = featureNameProvider.shortName(language)
  def preferredName(language: Language): String = featureNameProvider.preferredName(language)
  def localizedNames: List[LocalizedName] = featureNameProvider.localizedNames
  def userFriendlyCode: Option[String] = Some(code)

  // ParentAdministrativeEntityProvider
  def parentAdministrativeEntity: Option[AdministrativeEntity] = parentAdministrativeEntityProvider.parentAdministrativeEntity
}