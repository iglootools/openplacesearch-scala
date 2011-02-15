package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.feature.{FeatureNameProvider, ParentAdministrativeEntityProvider}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

trait AdministrativeEntity extends ParentAdministrativeEntityProvider with FeatureNameProvider with CountryProvider {
  /**
   * ADM0 (Country), ADM1 (First Level ADM), ADM2, ...)
   */
  def administrativeDivisionLevel: Int = parentAdministrativeEntity.map {_.administrativeDivisionLevel}.getOrElse(-1) + 1
  def parentAdministrativeEntityHavingLevel(level: Int): AdministrativeEntity = {
    val currentLevel = administrativeDivisionLevel
    require(level <= currentLevel && level >= 0, "requested level should be [0-%d]. 0 = Country, 1 = ADM1, 2 = ADM2, ...)".format(currentLevel))

    level match {
      case l if l == currentLevel => this
      case _ =>
        assume(parentAdministrativeEntity != None)
        parentAdministrativeEntity.map {_.parentAdministrativeEntityHavingLevel(level) }.get
    }
  }

  // CurrencyProvider
  def country: Country = {
    val administrativeEntityAtLevelZero = parentAdministrativeEntityHavingLevel(0)
    administrativeEntityAtLevelZero match {
      case c: Country =>  c
      case _ => throw new RuntimeException("By design, ADM0 should be a country")
    }
  }

  def childAdministrativeDivisions: List[AdministrativeDivision]
}