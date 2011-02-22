package org.iglootools.openplacesearch.api.administrativedivision

import java.util.Locale
import com.ibm.icu.util.ULocale

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait PreferredLocalesProvider {
  /**
   * Ordered by importance. may contain zero elements
   */
  def preferredLocales : List[ULocale]
}