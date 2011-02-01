package com.sirika.openplacesearch.api.formatting

import com.sirika.openplacesearch.api.feature.NamePart

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected final class ShortUsStyleNamesExtractor extends NamesExtractor {
  def extractRelevantNames(nameParts: List[NamePart]): List[String] = {
    require(nameParts.size >= 3, "There should be at least 3 NameParts : a Country, a first-order administrative division, and a name")

    val featureName = nameParts(nameParts.size -1).name
    val adm1CodePart = nameParts(1)
    val adm1Code = adm1CodePart.userFriendlyCode.getOrElse(adm1CodePart.name)
    List(adm1Code, featureName)
  }
}