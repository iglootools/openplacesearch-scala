package com.sirika.openplacesearch.api.commons

import com.sirika.openplacesearch.api.language.Language
import com.sirika.openplacesearch.api.commons.{LocalizedName, NamePart}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait FeatureNameProvider {
  self: ParentAdministrativeEntityProvider =>

  /**
   * Extracts the different components that form
   * the fully qualified name of the feature
   * @see NamePart
   */
  def fullyQualifiedNameParts : List[NamePart] = parentAdministrativeEntity.map {_.fullyQualifiedNameParts}
    .getOrElse(Nil) ::: List(NamePart(name=name, userFriendlyCode=userFriendlyCode))

  /**
   * A name that we use when no better name exists.
   * <p>
   * Might include non ASCII characters.
   * </p>
   */
  def name:String

  /**
   * A code such as CA for california, that is supposed to mean something to human beings.
   *
   * <p> features that have user friendly codes include US states and countries.
   * </p>
   */
  def userFriendlyCode: Option[String]

  def localizedNames:List[LocalizedName]
  /**
   * reverts to #name if no preferredName found
   */
  def preferredName(language: Language):String

  /**
   * reverts to #name if no short name found
   */
  def shortName(language: Language): String
}