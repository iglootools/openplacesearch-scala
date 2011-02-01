package com.sirika.openplacesearch.api.formatting

import com.sirika.openplacesearch.api.feature.NamePart

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected final class VeryShortQualifiedNamesExtractor extends NamesExtractor {
  def extractRelevantNames(nameParts: List[NamePart]): List[String] = {
    require(nameParts.size >= 2, "There should be at least 2 NameParts : a Country and a name")

    val feature = nameParts(nameParts.size -1)
    val country = nameParts(1)
    List(country, feature) map {_.name}
  }
}