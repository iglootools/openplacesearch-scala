package com.sirika.openplacesearch.api.formatting

import com.sirika.openplacesearch.api.feature.FeatureNameProvider

/**
 * @param Whether to reverse the order of the names (defaults to true)
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
protected final class ConcatenatingNameFormatter(
  val separator: String = ",",
  val namesExtractor: NamesExtractor = new FullyQualifiedNamesExtractor(),
  val reverse: Boolean = true) extends NameFormatter {

  require(separator != null, "separator is required")
  require(namesExtractor != null, "namesExtractor is required")

  def formatName(nameProvider: FeatureNameProvider) = {
    val names = namesExtractor.extractRelevantNames(nameProvider.fullyQualifiedNameParts).mkString(separator)
    if(reverse)
      names.reverse
    else
      names
  }
}