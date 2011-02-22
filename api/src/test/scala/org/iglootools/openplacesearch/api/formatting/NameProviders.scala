package org.iglootools.openplacesearch.api.formatting

import org.iglootools.openplacesearch.api.language.Language
import org.iglootools.openplacesearch.api.feature.{ParentAdministrativeEntityProvider, NamePart, LocalizedName, FeatureNameProvider}
import org.iglootools.openplacesearch.api.administrativedivision.AdministrativeEntity

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object NameProviders {
  def losAngeles: FeatureNameProvider =
    new StaticNameProvider(
      NamePart(name="United States", userFriendlyCode=Some("US")),
      NamePart(name="California", userFriendlyCode=Some("CA")),
      NamePart(name="Los Angeles County", userFriendlyCode=None),
      NamePart(name="Los Angeles", userFriendlyCode=None))

  def twoPartsOnly: FeatureNameProvider =
    new StaticNameProvider(
      NamePart(name="United States", userFriendlyCode=Some("US")),
      NamePart(name="California", userFriendlyCode=Some("CA")))

  def onePartOnly: FeatureNameProvider =
    new StaticNameProvider(
      NamePart(name="United States", userFriendlyCode=Some("US")))
}


private class StaticNameProvider(val nameParts: NamePart*) extends FeatureNameProvider with ParentAdministrativeEntityProvider {
  override def fullyQualifiedNameParts : List[NamePart] = nameParts.toList
  def shortName(language: Language): String = throw new RuntimeException("Not implemented")
  def preferredName(language: Language): String = throw new RuntimeException("Not implemented")
  def localizedNames: List[LocalizedName] = throw new RuntimeException("Not implemented")
  def userFriendlyCode: Option[String] = throw new RuntimeException("Not implemented")
  def name: String = throw new RuntimeException("Not implemented")
  def parentAdministrativeEntity: Option[AdministrativeEntity] = throw new RuntimeException("Not implemented")
}