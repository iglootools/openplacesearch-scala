package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.gisfeature.{ParentAdministrativeEntityProvider, FeatureNameProvider}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait AdministrativeEntity extends ParentAdministrativeEntityProvider with FeatureNameProvider {
  /**
   * ADM0 (Country), ADM1 (First Level ADM), ADM2, ...)
   */
  def administrativeDivisionLevel: Int
  def parentAdministrativeEntity: Option[AdministrativeEntity]
  def parentAdministrativeEntity(level: Int)
  def country : Country
}