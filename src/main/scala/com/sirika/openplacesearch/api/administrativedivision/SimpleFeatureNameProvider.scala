package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.feature.{ParentAdministrativeEntityProvider, FeatureNames, LocalizedName, FeatureNameProvider}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class SimpleFeatureNameProvider(
  val defaultName: String,
  val parentAdministrativeEntity:Option[AdministrativeEntity])
  extends FeatureNameProvider with ParentAdministrativeEntityProvider {

  require(Option(defaultName) exists {_.nonEmpty}, "the name is required")
  require(parentAdministrativeEntity != null, "parentAdministrativeEntity must be a non-null Option")

  def featureNames = FeatureNames(defaultName = defaultName)

  // FeatureNameProvider
  def name: String = featureNames.defaultName
  def shortName(language: Language): String = featureNames.defaultName
  def preferredName(language: Language): String = featureNames.preferredName(language)
  def localizedNames: List[LocalizedName] = featureNames.localizedNames
  def userFriendlyCode: Option[String] = None

}