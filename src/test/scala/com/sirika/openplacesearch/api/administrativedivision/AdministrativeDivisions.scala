package com.sirika.openplacesearch.api.administrativedivision

import com.sirika.openplacesearch.api.continent.Continents
import com.sirika.openplacesearch.api.geonames._
import com.sirika.openplacesearch.api.feature._
import com.sirika.openplacesearch.api.administrativedivision._
import org.joda.time.DateTimeZone
import com.ibm.icu.util.{ULocale, Currency}

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object AdministrativeDivisions {
  object France {
    def ileDeFranceAdm1: AdministrativeDivision =
      AdministrativeDivision(
        code="A8",
        featureNameProvider=SimpleFeatureNameProvider(defaultName="Île-de-France", parentAdministrativeEntity=Some(Countries.france)),
        parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(Countries.france))
    )

    def yvelinesAdm2: AdministrativeDivision =
      AdministrativeDivision(
        code="78",
        featureNameProvider=SimpleFeatureNameProvider(defaultName="Département des Yvelines", parentAdministrativeEntity=Some(ileDeFranceAdm1)),
        parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(ileDeFranceAdm1))
    )

    def arrondissementDeRambouilletAdm3: AdministrativeDivision =
      AdministrativeDivision(
        code="782",
        featureNameProvider=SimpleFeatureNameProvider(defaultName="Arrondissement de Rambouillet", parentAdministrativeEntity=Some(yvelinesAdm2)),
        parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(yvelinesAdm2))
    )
    def gazeranAdm4: AdministrativeDivision =
      AdministrativeDivision(
        code="78269",
        featureNameProvider=SimpleFeatureNameProvider(defaultName="Gazeran", parentAdministrativeEntity=Some(arrondissementDeRambouilletAdm3)),
        parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(arrondissementDeRambouilletAdm3))
    )
  }

}