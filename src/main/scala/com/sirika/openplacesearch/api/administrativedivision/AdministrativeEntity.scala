package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.feature.{FeatureNameProvider, ParentAdministrativeEntityProvider}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait AdministrativeEntity extends ParentAdministrativeEntityProvider with FeatureNameProvider {
  /**
   * ADM0 (Country), ADM1 (First Level ADM), ADM2, ...)
   */
  def administrativeDivisionLevel: Int = parentAdministrativeEntity.map {_.administrativeDivisionLevel}.getOrElse(-1) + 1
  def parentAdministrativeEntityW(level: Int): AdministrativeEntity = {
    val currentLevel = administrativeDivisionLevel
    require(level <= currentLevel && level >= 0, "requested level should be [0-%d]. 0 = Country, 1 = ADM1, 2 = ADM2, ...)".format(currentLevel))

    level match {
      case l if l == currentLevel => this
      case _ =>
        assume(parentAdministrativeEntity != None)
        parentAdministrativeEntity.map {_.parentAdministrativeEntityW(level) }.get
    }
  }
}