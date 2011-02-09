package com.sirika.openplacesearch.api.feature

import com.google.common.io.InputSupplier
import com.sirika.openplacesearch.api.language.Language


/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class FeatureNames(val defaultName: String, val localizedNamesSupplier: InputSupplier[List[LocalizedName]] = new InMemoryLocalizedNamesSupplier()) {
  require(Option(defaultName) exists {_.nonEmpty}, "defaultName is required")
  require(localizedNamesSupplier !=  null, "localizedNamesSupplier is required")

  def localizedNames: List[LocalizedName] = localizedNamesSupplier.getInput
  /**
   * reverts to #name if no preferredName found
   */
  def preferredName(language: Language) :String = {
    val names = namesInLanguage(language)
    names.find {n => n.preferred}.orElse(names.headOption).map{_.name}.getOrElse(defaultName)
  }

  /**
   * reverts to #name if no short name found
   */
  def shortName(language: Language) : String = {
    val names = namesInLanguage(language)
    names.find {n => n.shortName}.orElse(names.headOption).map{_.name}.getOrElse(defaultName)
  }

  private def namesInLanguage(language: Language) = localizedNames.filter {n:LocalizedName=> n.language == language}

}