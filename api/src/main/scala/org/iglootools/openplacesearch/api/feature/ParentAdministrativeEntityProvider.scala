package org.iglootools.openplacesearch.api.feature

import org.iglootools.openplacesearch.api.administrativedivision.AdministrativeEntity

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait ParentAdministrativeEntityProvider {
  def parentAdministrativeEntity: Option[AdministrativeEntity]
}