package org.iglootools.openplacesearch.api.administrativedivision

import org.iglootools.openplacesearch.api.feature.ParentAdministrativeEntityProvider

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[openplacesearch] final case class SimpleParentAdministrativeEntityProvider(val parentAdministrativeEntity: Option[AdministrativeEntity]) extends ParentAdministrativeEntityProvider {
  require(parentAdministrativeEntity != null, "parentAdministrativeEntity must be a non-null Option")
}