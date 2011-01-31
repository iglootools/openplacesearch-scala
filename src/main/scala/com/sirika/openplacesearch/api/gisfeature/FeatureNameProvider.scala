package com.sirika.openplacesearch.api.gisfeature

import com.sirika.openplacesearch.api.language.Language

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait FeatureNameProvider {
  self: {
    def featureNames: FeatureNames
  } =>

  def fullyQualifiedNameParts : List[NamePart]

  /**
   * A name that we use when no better name exists.
   * <p>
   * Might include non ASCII characters.
   * </p>
   */
  def name:String = self.featureNames.defaultName

  /**
   * A code such as CA for california, that is supposed to mean something to human beings.
   *
   * <p> features that have user friendly codes include US states and countries.
   * </p>
   */
  def userFriendlyCode: Option[String] = None

  def localizedNames:List[LocalizedName] = self.featureNames.localizedNames
  /**
   * reverts to #name if no preferredName found
   */
  def preferredName(language: Language):String = self.featureNames.preferredName(language)

  /**
   * reverts to #name if no short name found
   */
  def shortName(language: Language): String = self.featureNames.shortName(language)


}