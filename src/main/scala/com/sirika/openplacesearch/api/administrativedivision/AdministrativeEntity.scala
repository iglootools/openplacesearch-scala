package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.gisfeature.FeatureNameProvider

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait AdministrativeEntity {
  /**
   * ADM0 (Country), ADM1 (First Level ADM), ADM2, ...)
   */
  def administrativeDivisionLevel: Int
  def parentAdministrativeEntity: Option[AdministrativeEntity]
  def parentAdministrativeEntity(level: Int)
  def country : Country
}