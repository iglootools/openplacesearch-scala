package org.iglootools.openplacesearch.api.administrativedivision

import com.ibm.icu.util.Currency

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait CurrencyProvider {

  /**
   * Antarctica does not have a currency
   */
  def currency: Option[Currency]
}