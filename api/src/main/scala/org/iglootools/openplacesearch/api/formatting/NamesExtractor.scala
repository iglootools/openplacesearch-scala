package org.iglootools.openplacesearch.api.formatting

import org.iglootools.openplacesearch.api.feature.NamePart

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

/**
 * Extracts the names that are relevant
 */
protected[formatting] trait NamesExtractor {
  def extractRelevantNames(nameParts: List[NamePart]): List[String]
}