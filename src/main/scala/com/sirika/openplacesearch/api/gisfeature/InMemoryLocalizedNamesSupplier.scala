package com.sirika.openplacesearch.api.gisfeature

import com.google.common.io.InputSupplier


/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class InMemoryLocalizedNamesSupplier(val localizedNames: Iterable[LocalizedName]=List()) extends InputSupplier[List[LocalizedName]] {
  def getInput: List[LocalizedName] = localizedNames.toList
}