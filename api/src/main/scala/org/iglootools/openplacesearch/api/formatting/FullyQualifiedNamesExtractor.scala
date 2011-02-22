package org.iglootools.openplacesearch.api.formatting

import org.iglootools.openplacesearch.api.feature.NamePart

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[formatting] final class FullyQualifiedNamesExtractor extends NamesExtractor {
  def extractRelevantNames(nameParts: List[NamePart]): List[String] = nameParts map{_.name} toList
}