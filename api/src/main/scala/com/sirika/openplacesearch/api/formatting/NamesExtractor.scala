package com.sirika.openplacesearch.api.formatting

import com.sirika.openplacesearch.api.feature.NamePart

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

/**
 * Extracts the names that are relevant
 */
protected[formatting] trait NamesExtractor {
  def extractRelevantNames(nameParts: List[NamePart]): List[String]
}