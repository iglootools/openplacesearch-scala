package com.sirika.openplacesearch.api.feature

import com.google.common.io.InputSupplier


/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[api] final case class InMemoryLocalizedNamesSupplier(val localizedNames: Iterable[LocalizedName]=List()) extends InputSupplier[List[LocalizedName]] {
  def getInput: List[LocalizedName] = localizedNames.toList
}