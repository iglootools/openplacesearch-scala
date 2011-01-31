package com.sirika.openplacesearch.api.gisfeature

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
  def preferredName(language: Language) :String = localizedNames.find {n: LocalizedName => n.preferred && n.language == language}
    .map{ _.name}
    .getOrElse(defaultName)

  /**
   * reverts to #name if no short name found
   */
  def shortName(language: Language) : String = localizedNames.find {n: LocalizedName => n.shortName && n.language == language}
    .map {_.name}
    .getOrElse(defaultName)

}