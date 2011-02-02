package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.feature.ParentAdministrativeEntityProvider

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

final case class SimpleParentAdministrativeEntityProvider(val parentAdministrativeEntity: Option[AdministrativeEntity]) extends ParentAdministrativeEntityProvider