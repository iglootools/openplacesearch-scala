package com.sirika.openplacesearch.api.administrativedivision

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait PhonePrefixProvider {
  def phonePrefix: Option[String]
}