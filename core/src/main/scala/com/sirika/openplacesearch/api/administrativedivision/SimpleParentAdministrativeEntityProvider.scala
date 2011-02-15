package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.feature.ParentAdministrativeEntityProvider

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

protected[api] final case class SimpleParentAdministrativeEntityProvider(val parentAdministrativeEntity: Option[AdministrativeEntity]) extends ParentAdministrativeEntityProvider {
  require(parentAdministrativeEntity != null, "parentAdministrativeEntity must be a non-null Option")
}