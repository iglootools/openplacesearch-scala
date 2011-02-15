package com.sirika.openplacesearch.api.formatting.internal

import com.sirika.openplacesearch.api.feature.NamePart

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[formatting] final class ShortQualifiedNamesExtractor extends NamesExtractor {
  def extractRelevantNames(nameParts: List[NamePart]): List[String] = {
    require(nameParts.size >= 3, "There should be at least 3 NameParts : a Country, a first-order administrative division, and a name")

    val feature = nameParts(nameParts.size -1)
    val adm1 = nameParts(1)
    val country = nameParts(0)
    List(country, adm1, feature) map {_.name}
  }
}