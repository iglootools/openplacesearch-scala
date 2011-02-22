package org.iglootools.openplacesearch.api.administrativedivision

import org.iglootools.openplacesearch.api.language.Language
import org.iglootools.openplacesearch.api.feature._
/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[api] final case class SimpleFeatureNameProvider(val defaultName: String,
                                           val parentAdministrativeEntity:Option[AdministrativeEntity],
                                           val names: Iterable[LocalizedName] = List())
  extends FeatureNameProvider with ParentAdministrativeEntityProvider {

  require(Option(defaultName) exists {_.nonEmpty}, "the name is required")
  require(parentAdministrativeEntity != null, "parentAdministrativeEntity must be a non-null Option")
  require(names != null, "names is required")
  def featureNames = FeatureNames(defaultName = defaultName, localizedNamesSupplier = new InMemoryLocalizedNamesSupplier(names))

  // FeatureNameProvider
  def name: String = featureNames.defaultName
  def shortName(language: Language): String = featureNames.defaultName
  def preferredName(language: Language): String = featureNames.preferredName(language)
  def localizedNames: List[LocalizedName] = featureNames.localizedNames
  def userFriendlyCode: Option[String] = None

}