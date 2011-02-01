package com.sirika.openplacesearch.api.administrativedivision

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait TopLevelDomainProvider {
  /**
   * Kosovo does not have a TLD
   */
  def topLevelDomain: Option[String]
}