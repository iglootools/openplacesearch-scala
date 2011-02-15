package com.sirika.openplacesearch.api.administrativedivision

/**
 * @author Sami Dalouche (sami.dalouche@gmail.com)
 */

object AdministrativeDivisions {
  object France {
    def ileDeFrance: AdministrativeDivision =
      AdministrativeDivision(
        code="A8",
        featureNameProvider=SimpleFeatureNameProvider(defaultName="Île-de-France", parentAdministrativeEntity=Some(Countries.france)),
        parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(Countries.france))
      )

    object IleDeFrance {

      def paris: AdministrativeDivision =
        AdministrativeDivision(
          code="75",
          featureNameProvider=SimpleFeatureNameProvider(defaultName="Ville-de-Paris", parentAdministrativeEntity=Some(ileDeFrance)),
          parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(ileDeFrance))
        )

      def yvelines: AdministrativeDivision =
        AdministrativeDivision(
          code="78",
          featureNameProvider=SimpleFeatureNameProvider(defaultName="Département des Yvelines", parentAdministrativeEntity=Some(ileDeFrance)),
          parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(ileDeFrance))
        )

      object Paris {
        def arrondissementDeParis: AdministrativeDivision =
          AdministrativeDivision(
            code="751",
            featureNameProvider=SimpleFeatureNameProvider(defaultName="Arrondissement de Paris", parentAdministrativeEntity=Some(paris)),
            parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(paris))
          )
        object ArrondissementDeParis {
          def paris: AdministrativeDivision =
            AdministrativeDivision(
              code="75056",
              featureNameProvider=SimpleFeatureNameProvider(defaultName="Paris", parentAdministrativeEntity=Some(arrondissementDeParis)),
              parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(arrondissementDeParis))
            )
        }

      }

      object Yvelines {
        def arrondissementDeRambouillet: AdministrativeDivision =
          AdministrativeDivision(
            code="782",
            featureNameProvider=SimpleFeatureNameProvider(defaultName="Arrondissement de Rambouillet", parentAdministrativeEntity=Some(yvelines)),
            parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(yvelines))
          )

        object ArrondissementDeRambouillet {
          def gazeran: AdministrativeDivision =
            AdministrativeDivision(
              code="78269",
              featureNameProvider=SimpleFeatureNameProvider(defaultName="Gazeran", parentAdministrativeEntity=Some(arrondissementDeRambouillet)),
              parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(arrondissementDeRambouillet))
            )

          def rambouillet: AdministrativeDivision =
            AdministrativeDivision(
              code="78517",
              featureNameProvider=SimpleFeatureNameProvider(defaultName="Rambouillet", parentAdministrativeEntity=Some(arrondissementDeRambouillet)),
              parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(arrondissementDeRambouillet))
            )
        }


      }
    }

  }

  object UnitedStates {
    def california: AdministrativeDivision =
      AdministrativeDivision(
        code="CA",
        featureNameProvider=SimpleFeatureNameProvider(defaultName="California", parentAdministrativeEntity=Some(Countries.unitedStates)),
        parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(Countries.unitedStates))
      )

    object California {
      def losAngelesCounty: AdministrativeDivision =
        AdministrativeDivision(
          code="037",
          featureNameProvider=SimpleFeatureNameProvider(defaultName="Los Angeles County", parentAdministrativeEntity=Some(california)),
          parentAdministrativeEntityProvider=SimpleParentAdministrativeEntityProvider(parentAdministrativeEntity = Some(california))
        )

    }
  }

}