package com.sirika.openplacesearch.api.administrativedivision

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait PostalCodeFormatProvider {
  def postalCodeMask: Option[String]
  def postalCodeRegex: Option[String]
}