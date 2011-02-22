package org.iglootools.openplacesearch.api.formatting

import org.iglootools.openplacesearch.api.feature.FeatureNameProvider

/**
 * @param Whether to reverse the order of the names (defaults to true)
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */
protected[formatting] final class ConcatenatingNameFormatter(
  val separator: String = ", ",
  val namesExtractor: NamesExtractor = new FullyQualifiedNamesExtractor(),
  val reverse: Boolean = true) extends NameFormatter {

  require(separator != null, "separator is required")
  require(namesExtractor != null, "namesExtractor is required")

  def formatName(nameProvider: FeatureNameProvider): String = {
    val nameParts = namesExtractor.extractRelevantNames(nameProvider.fullyQualifiedNameParts)
    (if(reverse) nameParts.reverse else nameParts).mkString(separator)
  }
}