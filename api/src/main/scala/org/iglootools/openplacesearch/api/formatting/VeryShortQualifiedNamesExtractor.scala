package org.iglootools.openplacesearch.api.formatting

import org.iglootools.openplacesearch.api.feature.NamePart

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[formatting] final class VeryShortQualifiedNamesExtractor extends NamesExtractor {
  def extractRelevantNames(nameParts: List[NamePart]): List[String] = {
    require(nameParts.size >= 2, "There should be at least 2 NameParts : a Country and a name")

    val feature = nameParts(nameParts.size -1)
    val country = nameParts(0)
    List(country, feature) map {_.name}
  }
}